/*
 * (c) Copyright 2012 EVRYTHNG Ltd London / Zurich
 * www.evrythng.net
 * 
 */
package net.evrythng.thng.api.wrapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.evrythng.thng.api.model.Model;
import net.evrythng.thng.api.model.Property;
import net.evrythng.thng.api.model.Thng;
import net.evrythng.thng.api.model.ThngCollection;
import net.evrythng.thng.api.result.EvrythngResult;
import net.evrythng.thng.api.result.ThngArrayResult;
import net.evrythng.thng.api.search.GeoCode;
import net.evrythng.thng.api.search.SearchParameter;
import net.evrythng.thng.api.search.SearchParameter.Type;
import net.evrythng.thng.api.utils.HttpComponentsUtils;
import net.evrythng.thng.api.utils.JSONUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Wrapper for the REST API of EVRYTHNG.net.
 * 
 * @author <a href="mailto:pedro@evrythng.net">Pedro De Almeida</a>
 */
public class ThngAPIWrapper {

    private static final Logger logger = LoggerFactory.getLogger(ThngAPIWrapper.class);
    /**
     * API token for HTTP authentication.
     */
    private final String token;
    /**
     * Client HTTP connection.
     */
    private final HttpClient httpClient = new DefaultHttpClient();

    /**
     * Creates a new instance of {@link ThngAPIWrapper} using the provided
     * <code>token</code> for authentication.
     * 
     * {@link ThngAPIWrapper} uses values from the {@link Configuration} class
     * which is initialized with the <code>config.properties</code> file.
     * 
     * @see Configuration
     * @param token
     *            the API token for HTTP authentication
     */
    public ThngAPIWrapper(String token) {
        this.token = token;
    }

    /**
     * Adds required headers to the provided {@link HttpRequestBase} object.
     * 
     * @param httpRequest
     *            the request object to which headers will be added
     */
    private void addHeaders(HttpRequestBase httpRequest) {
        httpRequest.setHeader("Content-Type", Configuration.HTTP_CONTENT_TYPE);
        httpRequest.setHeader("Accept", Configuration.HTTP_ACCEPT);
        httpRequest.setHeader("X-EVRYTHNG-Token", this.token);
    }

    /**
     * Creates an URI for accessing a specific <code>path</code> from EVRYTHNG's
     * API.
     * 
     * @see #createURI(String, List)
     * @param path
     * @return
     * @throws URISyntaxException
     */
    private URI createURI(String path) throws URISyntaxException {
        // Delegate:
        return this.createURI(path, null);
    }

    /**
     * Creates an URI using the provided query <code>parameters</code> for
     * accessing a specific <code>path</code> from EVRYTHNG's API.
     * 
     * @param path
     * @param queryParams
     * @return
     * @throws URISyntaxException
     */
    private URI createURI(String path, List<NameValuePair> queryParams) throws URISyntaxException {
        String query = queryParams != null ? URLEncodedUtils.format(queryParams, Configuration.ENCODING) : null;
        return URIUtils.createURI(Configuration.SERVER_SCHEME, Configuration.SERVER_HOST, Configuration.SERVER_PORT, path, query, null);
    }

	/**
	 * Checks the status code of the response. The method throws an IOException
	 * if it is not one of the "success" codes in the 2XX range. The HTTP status
	 * is passed as a diagnostic message in the exception. If the response
	 * contains a <em>message</em> property it is appended.
	 * 
	 * @param response
	 * @throws IOException
	 */
    private void checkResponse(HttpResponse response) throws IOException {
    	StatusLine status = response.getStatusLine();
		switch (status.getStatusCode() / 100) {
		case 2: // HTTP/1.1 2XX
			break;
		default: 
			JSONObject jobj = HttpComponentsUtils.toJSONObject(response);
			Object message = jobj == null ? null : jobj.get("message");
			String diagnostic = message == null ? status.toString() : 
				String.format("%s (%s)", status.toString(), message.toString());
			throw new IOException(diagnostic);
		}
	}
    
    /**
     * Executes the given {@link HttpRequestBase} request. This method also adds
     * the required headers for accessing the <a
     * href="http://evrythng.net">EVRYTHNG.net</a> API.
     * 
     * @see #addHeaders(HttpRequestBase)
     * @param request
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    private HttpResponse execute(HttpRequestBase request) throws ClientProtocolException, IOException {
        logger.debug(">> Executing request: {}", request.getRequestLine());

        // Debug only:
        HttpComponentsUtils.printResquestHeaders(request); // FIXME: useful?

        // Add required headers:
        addHeaders(request);

        // Execute request:
        HttpResponse response = httpClient.execute(request);

        // Debug only:
        // ThngAPIWrapperUtils.printResponseInfo(response);
        HttpComponentsUtils.printResponseHeaders(response);

        // make sure the response can be used
        checkResponse(response);
        
        return response;
    }

    /**
     * Executes the given {@link HttpEntityEnclosingRequestBase}
     * <code>request</code> after
     * encapsulating the provided {@link JSON} <code>content</code>.
     * 
     * @see #execute(HttpRequestBase)
     * @param request
     * @param content
     * @return
     * @throws IOException
     * @throws ClientProtocolException
     */
    private HttpResponse execute(HttpEntityEnclosingRequestBase request, JSON content) throws ClientProtocolException, IOException {
        logger.debug(">> Setting entity content: {}", content.toString());

        // Set entity content:
        request.setEntity(new StringEntity(content.toString()));

        // Delegate:
        return this.execute(request);
    }

    /**
     * Executes a {@link HttpGet} request to the given <code>path</code>.
     * 
     * @param path
     * @param content
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    private HttpResponse get(String path) throws URISyntaxException, ClientProtocolException, IOException {
        URI uri = this.createURI(path);
        HttpGet request = new HttpGet(uri);
        // Delegate:
        return this.execute(request);
    }

    /**
     * Executes a {@link HttpGet} request to the given <code>path</code> with
     * the provided <code>parameters</code> list.
     * 
     * @param path
     * @param parameters
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    private HttpResponse get(String path, List<NameValuePair> parameters) throws URISyntaxException, ClientProtocolException, IOException {
        URI uri = this.createURI(path, parameters);
        HttpGet request = new HttpGet(uri);
        // Delegate:
        return this.execute(request);
    }

    /**
     * Executes a {@link HttpPost} request to the given <code>path</code> using
     * the provided {@link JSON} content.
     * 
     * @param path
     * @param content
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    private HttpResponse post(String path, JSON content) throws URISyntaxException, ClientProtocolException, IOException {
        URI uri = this.createURI(path);
        HttpPost request = new HttpPost(uri);
        // Delegate:
        return this.execute(request, content);
    }

    /**
     * Executes a {@link HttpPut} request to the given <code>path</code> using
     * the provided {@link JSON} content.
     * 
     * @param path
     * @param content
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    private HttpResponse put(String path, JSON content) throws URISyntaxException, ClientProtocolException, IOException {
        URI uri = this.createURI(path);
        HttpPut request = new HttpPut(uri);
        // Delegate:
        return this.execute(request, content);
    }

    /**
     * Executes a {@link HttpDelete} request to the given <code>path</code>.
     * 
     * @param path
     * @param content
     * @return
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IOException
     */
    private HttpResponse delete(String path) throws URISyntaxException, ClientProtocolException, IOException {
        URI uri = this.createURI(path);
        HttpDelete request = new HttpDelete(uri);
        // Delegate:
        return this.execute(request);
    }

    /**
     * Search elements using the provided <code>parameters</code> collection.
     * 
     * @param parameters
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws ParseException
     * @throws JSONException
     * @throws URISyntaxException
     */
    private Collection<Thng> search(List<NameValuePair> parameters) throws URISyntaxException, ClientProtocolException, IOException {
        // Perform the GET request:
        HttpResponse response = this.get(Configuration.PATH_SEARCH, parameters);

        // Wrap response content into a collection of Thng:
        return JSONUtils.toCollection(HttpComponentsUtils.toJSONArray(response), Thng.class);
    }

    /* ***** PUBLIC METHODS ***** */

    /* *** GLOBAL *** */
    /**
     * Checks connectivity to the EVRYTHNG.net API.
     * 
     * @return <code>true</code> if connection succeeds (i.e. returns HTTP code
     *         200), <code>false</code> otherwise
     * 
     * @throws IOException
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * 
     */
    public boolean ping() throws ClientProtocolException, IOException, URISyntaxException {
        // Perform the GET request:
        HttpResponse response = this.get(Configuration.PATH_BASE);

        // Check returned status code:
        // TODO: log some information (ping delay, time,etc...)
        return response.getStatusLine().getStatusCode() == HttpURLConnection.HTTP_OK;
    }

    /* *** THNGS *** */
    /**
     * Gets the all accessible <code>thngs</code>.
     * 
     * @return The user <code>thng</code>'s collection as
     *         a Java collection.
     * @throws IOException
     * @throws ClientProtocolException
     * @throws JSONException
     * @throws IllegalStateException
     * @throws URISyntaxException
     */
    public List<Thng> getThngs() throws URISyntaxException, ClientProtocolException, IOException {
        // Perform the GET request:
        HttpResponse response = this.get(Configuration.PATH_THNGS);

        // Convert the HTTP response to a Java collection of thngs and return it:
        return (List<Thng>) JSONUtils.toCollection(HttpComponentsUtils.toJSONArray(response), Thng.class);
    }

    /**
     * Creates a {@link Thng} using the provided <code>thng</code> instance.
     * 
     * @param thng
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Thng createThng(Model thng) throws URISyntaxException, ClientProtocolException, IOException, InstantiationException, IllegalAccessException {
        // Perform the POST request:
        HttpResponse response = this.post(Configuration.PATH_THNGS, thng.toJSONObject());

        // Wrap response into a Thng:
        Thng result = JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Thng.class);
        return result;
    }

    /**
     * Creates multiple {@link Thng}s using the provided <code>thngs</code>
     * collection.
     * 
     * @param thng
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public ThngArrayResult createThngs(Model... thngs) throws URISyntaxException, ClientProtocolException, IOException {
        // Perform the POST request:
        HttpResponse response = this.post(Configuration.PATH_THNGS, JSONArray.fromObject(thngs, JSONUtils.getConfig()));

        // Wrap response into a ThngArrayResult:
        return new ThngArrayResult(response);
    }

    /**
     * Gets the {@link Thng} referenced by the provided <code>id</code>.
     * 
     * @param id
     *            the identifier referencing the targeted <code>thng</code>
     * @return the requested {@link Thng} object.
     * @throws IOException
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Thng getThng(String id) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
        // Perform the GET request:
        HttpResponse response = this.get(String.format(Configuration.PATH_THNG, id));

        // Wrap response into a Thng:
        return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Thng.class);
    }

    /**
     * Updates the {@link Thng}.
     * 
     * @param thng
     *            the {@link Thng} to update
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Thng updateThng(Thng thng) throws URISyntaxException, ClientProtocolException, IOException, InstantiationException, IllegalAccessException {
        // Perform the PUT request:
        HttpResponse response = this.put(String.format(Configuration.PATH_THNG, thng.getId()), thng.toJSONObject());

        // Wrap response into a Thng:
        return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Thng.class);
    }
    
    /**
     * Deletes the {@link Thng} referenced by the provided <code>id</code>.
     * 
     * TODO: check return type: EvrythngResult or void?
     * 
     * @param id
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public EvrythngResult deleteThng(String id) throws URISyntaxException, ClientProtocolException, IOException {
        // Perform the DELETE request:
        HttpResponse response = this.delete(String.format(Configuration.PATH_THNG, id));

        // Consume entity content to avoid connection errors:
        EntityUtils.consume(response.getEntity());

        // Wrap response into a EvrythngResult:
        return new EvrythngResult(response);
    }
    
        /**
     * Deletes the {@link Thng}.
     * 
     * TODO: check return type: EvrythngResult or void?
     * 
     * @param thng the {@link Thng} to be deleted.
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public EvrythngResult deleteThng(Thng thng) throws URISyntaxException, ClientProtocolException, IOException {
        return deleteThng(thng.getId());
    }

    /* *** PROPERTIES *** */
    /**
     * Creates a new property on the {@link Thng} referenced by the given
     * <code>id</code> using the provided {@link Property}.
     * 
     * @param id
     * @param property
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <K> Property<K> createProperty(Thng thng, Property<K> property) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
        return createProperty(thng.getId(), property);
    }
    
    
    
    /**
     * Creates a new property on the {@link Thng} referenced by the given
     * <code>id</code> using the provided {@link Property}.
     * 
     * @param thngId
     * @param property
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <K> Property<K> createProperty(String thngId, Property<K> property) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
        // Perform the POST request:
        HttpResponse response = this.post(String.format(Configuration.PATH_PROPERTIES, thngId), property.toJSONObject());

        // Wrap response into a Property:
        return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Property.class);
    }

    /**
     * Creates new properties on the {@link Thng} referenced by the given
     * <code>id</code> using the provided {@link Property} collection.
     * 
     * @param id
     * @param property
     * @return a {@link JSONArray} of {@link Property}'s
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public JSONArray createProperties(String thngId, Property<?>... properties) throws ClientProtocolException, URISyntaxException, IOException {
        // Encapsulate properties:
        JSONArray content = new JSONArray();
        for (Property<?> item : properties) {
            content.add(item.toJSONObject());
        }

        // Perform the POST request:
        HttpResponse response = this.post(String.format(Configuration.PATH_PROPERTIES, thngId), content);

        // Convert response content to JSON and return it:
        return HttpComponentsUtils.toJSONArray(response);
    }
    
    
    /**
     * Creates new properties on the {@link Thng} referenced by the given
     * <code>id</code> using the provided {@link Property} collection.
     * 
     * @param thng The {@link Thng} to which the properties should be added.
     * @param property
     * @return a {@link JSONArray} of {@link Property}'s
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public JSONArray createProperties(Thng thng, Property<?>... properties) throws ClientProtocolException, URISyntaxException, IOException {
        return createProperties(thng.getId(), properties);
    }

    /**
     * Gets all properties of the {@link Thng} referenced by the provided
     * <code>id</code>.
     * 
     * @param id
     *            the identifier referencing the targeted {@link Thng}
     * @return a {@link JSONArray} of {@link Property}
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public JSONArray getProperties(String id) throws ClientProtocolException, URISyntaxException, IOException {
        // Perform the GET request:
        HttpResponse response = this.get(String.format(Configuration.PATH_PROPERTIES, id));

        // Convert response content to JSON and return it:
        return HttpComponentsUtils.toJSONArray(response);
    }
    
    /**
     * Gets all properties of the {@link Thng}.
     * 
     * @param thng
     * @return a {@link JSONArray} of {@link Property}
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public JSONArray getProperties(Thng thng) throws ClientProtocolException, URISyntaxException, IOException {
        return getProperties(thng.getId());
    }
    
    

    /**
     * Gets the <code>key</code> property on the {@link Thng} referenced
     * by the provided <code>id</code>.
     * 
     * @param id
     * @param key
     * @return the last value of {@link Property} key
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <K> Property<K> getProperty(String id, String key) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
        // Perform the GET request:
        HttpResponse response = this.get(String.format(Configuration.PATH_PROPERTY, id, key));

        // Wrap response into a Property:
        return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Property.class); // FIXME: generic parameter is lost!
    }
    
    /**
     * Gets the <code>key</code> property of the {@link Thng}.
     * 
     * @param thng
     * @param key
     * @return the last value of {@link Property} key
     * @throws ClientProtocolException
     * @throws URISyntaxException
     * @throws IOException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <K> Property<K> getProperty(Thng thng, String key) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
        return getProperty(thng.getId(), key);
    }
    
    /**
     * This updates a {@link Property} of a {@link Thng}.
     * @param <K>
     * @param thng the {@link Thng} to be updated
     * @param property
     * @return
     * @throws URISyntaxException
     * @throws IOException 
     */
    public <K> Property<K> updateProperty(Thng thng, Property<K> property) throws URISyntaxException, IOException {
        return updateProperty(thng.getId(), property);
    }
    
    /**
     * This updates a {@link Property} of a {@link Thng}.
     * @param <K>
     * @param thngId
     * @param property
     * @return
     * @throws URISyntaxException
     * @throws IOException 
     */
    public <K> Property<K> updateProperty(String thngId, Property<K> property) throws URISyntaxException, IOException {
        // Perform the PUT request:
        HttpResponse response = this.put(String.format(Configuration.PATH_PROPERTY, thngId, property.getKey()), property.toJSONObject());

        // Wrap response into a Property:
        return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), Property.class);
    }

    /**
     * Deletes the <code>key</code> property on the {@link Thng} referenced by
     * the given <code>id</code>.
     * 
     * @param id
     * @param key
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public EvrythngResult deleteProperty(String id, String key) throws ClientProtocolException, URISyntaxException, IOException {
        // Perform the GET request:
        HttpResponse response = this.delete(String.format(Configuration.PATH_PROPERTY, id, key));

        // Consume entity content to avoid connection errors:
        EntityUtils.consume(response.getEntity());

        // Wrap response into a EvrythngResult:
        return new EvrythngResult(response);
    }

    /* *** COLLECTIONS *** */
    /**
     * Creates a collection of <code>thngs</code> using the provided
     * {@link CollectionObject}.
     * 
     * @param collection
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public ThngCollection createCollection(ThngCollection collection) throws ClientProtocolException, URISyntaxException, IOException, InstantiationException, IllegalAccessException {
        // Perform the POST request:
        HttpResponse response = this.post(Configuration.PATH_COLLECTIONS, collection.toJSONObject());

        // Wrap response into a ThngCollection:
        return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), ThngCollection.class);
    }

    /**
     * Gets all available collections.
     * 
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public Collection<ThngCollection> getCollections() throws ClientProtocolException, URISyntaxException, IOException {
        // Perform the GET request:
        HttpResponse response = this.get(Configuration.PATH_COLLECTIONS);

        // Wrap response content into a collection of ThngCollection:
        return JSONUtils.toCollection(HttpComponentsUtils.toJSONArray(response), ThngCollection.class);
    }

    /**
     * Gets the collection of <code>thngs</code> referenced by the provided
     * <code>id</code>.
     * 
     * @param id
     *            the identifier referencing the targeted collection of
     * @return the required ThngCollection.
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public ThngCollection getCollection(String id) throws ClientProtocolException, URISyntaxException, IOException {
        // Perform the GET request:
        HttpResponse response = this.get(String.format(Configuration.PATH_COLLECTION, id));

        // Wrap response content into a Collection:
        return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), ThngCollection.class);
    }
    
    /**
     * Gets all {@link Thngs} in a {@link ThngCollection}
     * @param collection the {@link ThngCollection} from which to get the {@link Thngs} 
     * @return
     * @throws IOException
     * @throws URISyntaxException 
     */
    public ThngArrayResult getThngsInCollection(ThngCollection collection) throws IOException, URISyntaxException {
        HttpResponse response = this.get(String.format(Configuration.PATH_COLLECTION_THNGS, collection.getId()));
        
        return new ThngArrayResult(response);
    }

    /**
     * Updates the {@link ThngCollection}.
     * 
     * @param collection
     *            the {@link ThngCollection} to update
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public ThngCollection updateCollection(ThngCollection collection) throws ClientProtocolException, URISyntaxException, IOException {
        // Perform the PUT request:
        HttpResponse response = this.put(String.format(Configuration.PATH_COLLECTION, collection.getId()), collection.toJSONObject());

        // Wrap response into a Thng:
        return JSONUtils.toBean(HttpComponentsUtils.toJSONObject(response), ThngCollection.class);
    }
    
    /**
     * Adds exiting {@link Thng}s to a {@link ThngCollection}.
     * @param collection the {@link ThngCollection} to update
     * @param thngs 1 .. {@link ThngCollection} to be added to the {@link ThngCollection}.
     * @return
     * @throws IOException
     * @throws URISyntaxException 
     */
    public ThngArrayResult addThngsToCollection(ThngCollection collection, Thng... thngs) throws IOException, URISyntaxException {
        List<String> ids = new ArrayList();
        for(Thng currentThng: thngs) {
                ids.add(currentThng.getId());
        }
        
        return addThngsToCollection(collection, ids);
    }
    
    /**
     * Adds exiting {@link Thng}s to a {@link ThngCollection}.
     * @param collection the {@link ThngCollection} to update
     * @param thngsId A list of IDs of {@link Thng}s to be added to the {@link ThngCollection}.
     * @return
     * @throws IOException
     * @throws URISyntaxException 
     */
    public ThngArrayResult addThngsToCollection(ThngCollection collection, List<String> thngsId) throws IOException, URISyntaxException {
        // Create the JSON array of IDs
        JSONArray ids = JSONUtils.toJSONArray(thngsId);
        HttpResponse response = this.post(String.format(Configuration.PATH_COLLECTION_THNGS, collection.getId()), ids); 
        
        return new ThngArrayResult(response);
    }
    
    /**
     * Removes all {@link Thng}s from a {@link ThngCollection}.
     * @param collection the {@link ThngCollection} to remove {@link Thng}s from
     * @return
     * @throws IOException
     * @throws URISyntaxException 
     */
    public EvrythngResult removeAllThngsFromCollection(ThngCollection collection) throws IOException, URISyntaxException {
        // Perform the DELETE request:
        HttpResponse response = this.delete(String.format(Configuration.PATH_COLLECTION_THNGS, collection.getId()));  
        
        // Consume entity content to avoid connection errors:
        EntityUtils.consume(response.getEntity());
        
        return new EvrythngResult(response);
    }
    
    /**
     * Removes a {@link Thng} from a {@link ThngCollection}.
     * @param collection the {@link ThngCollection} to remove the {@link Thng} from
     * @param thng the {@link Thng} to be removed from the {@link ThngCollection}
     * @return
     * @throws IOException
     * @throws URISyntaxException 
     */
    public EvrythngResult removeThngFromCollection(ThngCollection collection, Thng thng) throws IOException, URISyntaxException {
        // Perform the DELETE request:
        HttpResponse response = this.delete(String.format(Configuration.PATH_COLLECTION_THNG, collection.getId(), thng.getId())); 
        
        // Consume entity content to avoid connection errors:
        EntityUtils.consume(response.getEntity());
        
        return new EvrythngResult(response);
    }
    

    /**
     * Deletes the {@link ThngCollection} referenced by the provided
     * <code>id</code>.
     * 
     * TODO: check return type: EvrythngResult or void?
     * 
     * @param id
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public EvrythngResult deleteCollection(String id) throws URISyntaxException, ClientProtocolException, IOException {
        // Perform the DELETE request:
        HttpResponse response = this.delete(String.format(Configuration.PATH_COLLECTION, id));

        // Consume entity content to avoid connection errors:
        EntityUtils.consume(response.getEntity());

        // Wrap response into a EvrythngResult:
        return new EvrythngResult(response);
    }
    
    /**
     * Deletes the {@link ThngCollection}.
     * 
     * TODO: check return type: EvrythngResult or void?
     * 
     * @param collection a {@link ThngCollection} to be deleted.
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ClientProtocolException
     */
    public EvrythngResult deleteCollection(ThngCollection collection) throws URISyntaxException, ClientProtocolException, IOException {
        return deleteCollection(collection.getId());
    }

    /* *** SEARCH *** */
    /**
     * Searches for all <code>thngs</code> within user space.
     * 
     * FIXME: unrestricted search should not be allowed!
     * 
     * @return
     * @throws IOException
     * @throws URISyntaxException
     * @throws ClientProtocolException
     */
    public Collection<Thng> search() throws ClientProtocolException, URISyntaxException, IOException {
        // Delegate:
        return this.search(new ArrayList<NameValuePair>());
    }

    /**
     * Searches {@link Thng}s using provided <code>query</code> pattern.
     * The text of the query will be searched into to the name and description
     * fields (case-independant search) of all your thngs and all public thngs
     * 
     * <em>Note:</em> URL encoding will be performed on <code>query</code>.
     * 
     * @see #search(SearchParameter, String)
     * @param query
     * @return
     * @throws URISyntaxException
     * @throws IOException
     * @throws ParseException
     * @throws ClientProtocolException
     * @throws JSONException
     */
    public Collection<Thng> search(String query) throws JSONException, ClientProtocolException, ParseException, IOException, URISyntaxException {
        // Delegate:
        return this.search(SearchParameter.QUERY, query);
    }

    /**
     * Searches {@link Thng}s using provided {@link SearchParameter}
     * <code>parameter</code> and <code>value</code>.
     * 
     * <em>Note:</em> URL encoding will be performed on every parameter
     * <code>value</code>.
     * 
     * @see #search(List)
     * 
     * @param query
     * @param geoCode
     * @param type
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws ParseException
     * @throws JSONException
     * @throws URISyntaxException
     */
    public Collection<Thng> search(String query, GeoCode geoCode, Type type) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
        // Build query parameters:
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair(SearchParameter.QUERY.getName(), query));
        parameters.add(new BasicNameValuePair(SearchParameter.GEOCODE.getName(), geoCode.toString()));
        parameters.add(new BasicNameValuePair(SearchParameter.TYPE.getName(), type.toString()));

        // Delegate:
        return this.search(parameters);
    }

    /**
     * Searches {@link Thng}s using provided {@link SearchParameter}
     * <code>parameter</code> and <code>value</code>.
     * 
     * <em>Note:</em> URL encoding will be performed on provided parameter
     * <code>value</code>.
     * 
     * @see #search(List)
     * 
     * @param parameter
     * @param value
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     * @throws ParseException
     * @throws JSONException
     * @throws URISyntaxException
     */
    public Collection<Thng> search(SearchParameter parameter, String value) throws ClientProtocolException, IOException, ParseException, JSONException, URISyntaxException {
        // Build query parameters:
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair(parameter.toString(), value));

        // Delegate:
        return this.search(parameters);
    }
}