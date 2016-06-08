# EVRYTHNG Java SDK

This project is a Java library for communicating with the [EVRYTHNG API](https://developers.evrythng.com/). The goal of the library is to support 100% of the EVRYTHNG API.

Built versions of the EVRYTHNG Java SDK are currently available from the Sonatype OSS repository. Javadoc and source JARs are available as well from the Sonatype OSS repository.

## Packages

The library is composed of 3 main packages.

### Resource Model (com.evrythng.thng.resource.model)

This package contains all the model classes representing the resources available through the API such as thngs, products, collections, actions and properties. The model classes contains getters and setters for all the properties present in the EVRYTHNG API JSON response.

### Client (com.evrythng.java.wrapper)

This package contains classes communicate with the EVRYTHNG API over HTTPS. The client package is also responsible for converting JSON responses to appropriate Java model classes as well as generating request exceptions based on HTTP status codes. Service classes are defined for the resources they interact with such as ThngService, ProductService, and CollectionService.

### Commons (com.evrythng.commons)

This package contains classes useful to represent concepts that are across resources, such as pagination models, validation utilities and data structures. It is mostly for internal usage.

## Quick Tour

Let's see how we can get started with the EVRYTHNG Java SDK. This step by step guide is easy to read, but you can find the full quick tour class file [here](https://github.com/evrythng/evrythng-java-sdk/blob/master/evrythng-java-wrapper/src/main/java/com/evrythng/java/wrapper/examples/QuickTour.java).

### Setup

The easiest way to get started with the EVRYTHNG Java Client is to use a build automation tool.

#### [Maven](https://maven.apache.org/)

Inside `pom.xml`:

```xml
<dependency>
    <groupId>com.evrythng</groupId>
    <artifactId>evrythng-java-wrapper</artifactId>
    <version>1.26</version>
</dependency>
```

#### [Gradle](http://gradle.org/)

Inside `build.gradle`:

```
apply plugin: 'java'


repositories {
    mavenCentral()
}


dependencies {
    compile group: 'com.evrythng', name: 'evrythng-java-wrapper', version: '1.26'
}
```

### Authenticating

In order to start using the EVRYTHNG Java SDK, an API Key is needed, get one by registering on our [Dashboard](https://developers.evrythng.com). Once logged in, you can find it under 'Account settings' section of the menu.

In all the examples, we will assume you have an API Key stored in a variable called `apiKey`.

```
ApiManager client = new ApiManager(apiKey);
```

And you are good to go! The client is stateless, apart from credentials, so it is possible to share it across different parts of your application.

### Create a Product

Products are central in the EVRYTHNG API, so let's see how you can create one.

```
// we define what the Product will look like.
Product productToCreate = new Product();
productToCreate.setName("Ferrari F40");
productToCreate.setDescription("A mid-engine, rear-wheel drive, two-door coupé sports car built from 1987 to 1992.");
productToCreate.addCustomFields("category", "sport");
productToCreate.addCustomFields("type", "coupé");
productToCreate.setTags(Arrays.asList("car", "sport", "ferrari", "coupé"));
// here we actually create it using the EVRYTHNG API, after this line you can call product.getId() to retrieve the id assigned to it.
Product f40 = client.productService().productCreator(productToCreate).execute();
```

### Create Thngs

Products are great, but they represent abstractions, let's create a few concrete instances, namely Thngs.

One:

```
// our first Thng.
Thng myRedF40 = new Thng();
myRedF40.setName("My red Ferrari F40");
// it's an F40!
myRedF40.setProduct(f40.getId());
myRedF40.setDescription("It's red!");
myRedF40.addCustomFields("color", "red");
myRedF40.addCustomFields("license_plate", "NY - ABC 2345");
myRedF40.setTags(Arrays.asList("red", "US", "NY"));
myRedF40 = client.thngService().thngCreator(myRedF40).execute();
```

And another one:

```
// and another one.
Thng myYellowF40 = new Thng();
myYellowF40.setName("My yellow Ferrari F40");
// it's an F40!
myYellowF40.setProduct(f40.getId());
myYellowF40.setDescription("It's yellow! The original Ferrari color.");
myYellowF40.addCustomFields("color", "yellow");
myYellowF40.addCustomFields("license_plate", "NY - ABC 1981");
myYellowF40.setTags(Arrays.asList("yellow", "NY", "US"));
myYellowF40 = client.thngService().thngCreator(myYellowF40).execute();
```

### Update Properties

Thngs are awesome, for they can update their state. Normally they would do that automatically if they are connected to the EVRYTHNG API, but we can also do it manually.

First for our red car:

```
// define current state.
List<Property<?>> myRedF40State = new ArrayList<>();
myRedF40State.add(new NumberProperty("engine_temperature", 72.31));
myRedF40State.add(new BooleanProperty("started", true));
// perform the update.
client.thngService().propertiesCreator(myRedF40.getId(), myRedF40State).execute();
```

And then for the yellow one:

```
// define current state.
List<Property<?>> myYellowF40State = new ArrayList<>();
myYellowF40State.add(new NumberProperty("engine_temperature", 12.23));
myYellowF40State.add(new BooleanProperty("started", false));
// perform the update.
client.thngService().propertiesCreator(myYellowF40.getId(), myYellowF40State).execute();
```
### Retrieve Thngs

We can iterate all our Thngs, as follows:

```
// iterating all Thngs.
Iterator<PVector<Thng>> allMyThngs = client.thngService().iterator().perPage(10).execute();
while (allMyThngs.hasNext()) {
    PVector<Thng> page = allMyThngs.next();
    // do something with the page
    for (Thng thng : page) {
        System.out.println("Retrieved Thng with name: " + thng.getName());
    }
}
```

Or we can iterate Thngs based on filters, as an example of Tags:

```
// iterating only red Thngs.
Iterator<PVector<Thng>> redThngsOnly = client.thngService().iterator().perPage(10).filter("tags=red").execute();
    while (redThngsOnly.hasNext()) {
    PVector<Thng> page = redThngsOnly.next();
    // do something with the page
    for (Thng thng : page) {
        System.out.println("Retrieved Thng with name: " + thng.getName());
    }
}
```


## License

 Copyright EVRYTHNG Limited

   The EVRYTHNG API wrapper is licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

