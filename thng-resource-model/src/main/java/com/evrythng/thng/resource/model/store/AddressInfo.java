package com.evrythng.thng.resource.model.store;

import com.evrythng.commons.EnumUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the parts of an address, consistent with JSR 179-1
 * This format of addressing is deemed to have provisions for the majority of
 * global addresses.
 * Modelled from Location API for J2ME - javax.microedition.location.AddressInfo
 */
public class AddressInfo implements Serializable {

	private static final long serialVersionUID = -5588745503687191817L;

	/**
	 * Represents two-letter country codes
	 * ISO 3166-1-alpha-2 code
	 * Last updated: 2013-02-06
	 *
	 * @author colin
	 */
	public enum CountryCode {

		AF("AF", "AFGHANISTAN", "Afghanistan"), AX("AX", "ÅLAND ISLANDS", "Aland Islands"), AL("AL", "ALBANIA", "Albania"), DZ("DZ", "ALGERIA", "Algeria"), AS("AS", "AMERICAN SAMOA", "American Samoa"), AD("AD", "ANDORRA", "Andorra"), AO("AO",
		                                                                                                                                                                                                                                     "ANGOLA",
		                                                                                                                                                                                                                                     "Angola"), AI(
				"AI", "ANGUILLA", "Anguilla"), AQ("AQ", "ANTARCTICA", "Antarctica"), AG("AG", "ANTIGUA AND BARBUDA", "Antigua and Barbuda"), AR("AR", "ARGENTINA", "Argentina"), AM("AM", "ARMENIA", "Armenia"), AW("AW", "ARUBA", "Aruba"), AU("AU",
		                                                                                                                                                                                                                                        "AUSTRALIA",
		                                                                                                                                                                                                                                        "Australia"), AT(
				"AT", "AUSTRIA", "Austria"), AZ("AZ", "AZERBAIJAN", "Azerbaijan"), BS("BS", "BAHAMAS", "Bahamas"), BH("BH", "BAHRAIN", "Bahrain"), BD("BD", "BANGLADESH", "Bangladesh"), BB("BB", "BARBADOS", "Barbados"), BY("BY", "BELARUS",
		                                                                                                                                                                                                                      "Belarus"), BE("BE",
		                                                                                                                                                                                                                                     "BELGIUM",
		                                                                                                                                                                                                                                     "Belgium"), BZ(
				"BZ", "BELIZE", "Belize"), BJ("BJ", "BENIN", "Benin"), BM("BM", "BERMUDA", "Bermuda"), BT("BT", "BHUTAN", "Bhutan"), BO("BO", "BOLIVIA, PLURINATIONAL STATE OF", "Bolivia"), BQ("BQ", "BONAIRE, SINT EUSTATIUS AND SABA",
		                                                                                                                                                                                        "Bonaire, Saint Eustatius and Saba"), BA("BA",
		                                                                                                                                                                                                                                 "BOSNIA AND HERZEGOVINA",
		                                                                                                                                                                                                                                 "Bosnia and Herzegovina"), BW(
				"BW", "BOTSWANA", "Botswana"), BV("BV", "BOUVET ISLAND", "Bouvet Island"), BR("BR", "BRAZIL", "Brazil"), IO("IO", "BRITISH INDIAN OCEAN TERRITORY", "British Indian Ocean Territory"), BN("BN", "BRUNEI DARUSSALAM",
		                                                                                                                                                                                                  "Brunei Darussalam"), BG("BG", "BULGARIA",
		                                                                                                                                                                                                                           "Bulgaria"), BF("BF",
		                                                                                                                                                                                                                                           "BURKINA FASO",
		                                                                                                                                                                                                                                           "Burkina Faso"), BI(
				"BI", "BURUNDI", "Burundi"), KH("KH", "CAMBODIA", "Cambodia"), CM("CM", "CAMEROON", "Cameroon"), CA("CA", "CANADA", "Canada"), CV("CV", "CAPE VERDE", "Cape Verde"), KY("KY", "CAYMAN ISLANDS", "Cayman Islands"), CF("CF",
		                                                                                                                                                                                                                              "CENTRAL AFRICAN REPUBLIC",
		                                                                                                                                                                                                                              "Central African Republic"), TD(
				"TD", "CHAD", "Chad"), CL("CL", "CHILE", "Chile"), CN("CN", "CHINA", "China"), CX("CX", "CHRISTMAS ISLAND", "Christmas Island"), CC("CC", "COCOS (KEELING) ISLANDS", "Cocos (Keeling) Islands"), CO("CO", "COLOMBIA", "Colombia"), KM(
				"KM", "COMOROS", "Comoros"), CG("CG", "CONGO", "Congo"), CD("CD", "CONGO, THE DEMOCRATIC REPUBLIC OF THE", "Congo, The Democratic Republic of the"), CK("CK", "COOK ISLANDS", "Cook Islands"), CR("CR", "COSTA RICA", "Costa Rica"), CI(
				"CI", "CÔTE D'IVOIRE", "Cote d'Ivoire"), HR("HR", "CROATIA", "Croatia"), CU("CU", "CUBA", "Cuba"), CW("CW", "CURAÇAO", "Curacao"), CY("CY", "CYPRUS", "Cyprus"), CZ("CZ", "CZECH REPUBLIC", "Czech Republic"), DK("DK", "DENMARK",
		                                                                                                                                                                                                                          "Denmark"), DJ("DJ",
		                                                                                                                                                                                                                                         "DJIBOUTI",
		                                                                                                                                                                                                                                         "Djibouti"), DM(
				"DM", "DOMINICA", "Dominica"), DO("DO", "DOMINICAN REPUBLIC", "Dominican Republic"), EC("EC", "ECUADOR", "Ecuador"), EG("EG", "EGYPT", "Egypt"), SV("SV", "EL SALVADOR", "El Salvador"), GQ("GQ", "EQUATORIAL GUINEA",
		                                                                                                                                                                                                    "Equatorial Guinea"), ER("ER", "ERITREA",
		                                                                                                                                                                                                                             "Eritrea"), EE("EE",
		                                                                                                                                                                                                                                            "ESTONIA",
		                                                                                                                                                                                                                                            "Estonia"), ET(
				"ET", "ETHIOPIA", "Ethiopia"), FK("FK", "FALKLAND ISLANDS (MALVINAS)", "Falkland Islands (Malvinas)"), FO("FO", "FAROE ISLANDS", "Faroe Islands"), FJ("FJ", "FIJI", "Fiji"), FI("FI", "FINLAND", "Finland"), FR("FR", "FRANCE",
		                                                                                                                                                                                                                        "France"), GF("GF",
		                                                                                                                                                                                                                                      "FRENCH GUIANA",
		                                                                                                                                                                                                                                      "French Guiana"), PF(
				"PF", "FRENCH POLYNESIA", "French Polynesia"), TF("TF", "FRENCH SOUTHERN TERRITORIES", "French Southern Territories"), GA("GA", "GABON", "Gabon"), GM("GM", "GAMBIA", "Gambia"), GE("GE", "GEORGIA", "Georgia"), DE("DE", "GERMANY",
		                                                                                                                                                                                                                            "Germany"), GH("GH",
		                                                                                                                                                                                                                                           "GHANA",
		                                                                                                                                                                                                                                           "Ghana"), GI(
				"GI", "GIBRALTAR", "Gibraltar"), GR("GR", "GREECE", "Greece"), GL("GL", "GREENLAND", "Greenland"), GD("GD", "GRENADA", "Grenada"), GP("GP", "GUADELOUPE", "Guadeloupe"), GU("GU", "GUAM", "Guam"), GT("GT", "GUATEMALA", "Guatemala"), GG(
				"GG", "GUERNSEY", "Guernsey"), GN("GN", "GUINEA", "Guinea"), GW("GW", "GUINEA-BISSAU", "Guinea-Bissau"), GY("GY", "GUYANA", "Guyana"), HT("HT", "HAITI", "Haiti"), HM("HM", "HEARD ISLAND AND MCDONALD ISLANDS",
		                                                                                                                                                                              "Heard Island and McDonald Islands"), VA("VA",
		                                                                                                                                                                                                                       "HOLY SEE (VATICAN CITY STATE)",
		                                                                                                                                                                                                                       "Holy See (Vatican City State)"), HN(
				"HN", "HONDURAS", "Honduras"), HK("HK", "HONG KONG", "Hong Kong"), HU("HU", "HUNGARY", "Hungary"), IS("IS", "ICELAND", "Iceland"), IN("IN", "INDIA", "India"), ID("ID", "INDONESIA", "Indonesia"), IR("IR", "IRAN, ISLAMIC REPUBLIC OF",
		                                                                                                                                                                                                              "Iran, Islamic Republic of"), IQ(
				"IQ", "IRAQ", "Iraq"), IE("IE", "IRELAND", "Ireland"), IM("IM", "ISLE OF MAN", "Isle of Man"), IL("IL", "ISRAEL", "Israel"), IT("IT", "ITALY", "Italy"), JM("JM", "JAMAICA", "Jamaica"), JP("JP", "JAPAN", "Japan"), JE("JE", "JERSEY",
		                                                                                                                                                                                                                                "Jersey"), JO(
				"JO", "JORDAN", "Jordan"), KZ("KZ", "KAZAKHSTAN", "Kazakhstan"), KE("KE", "KENYA", "Kenya"), KI("KI", "KIRIBATI", "Kiribati"), KP("KP", "KOREA, DEMOCRATIC PEOPLE'S REPUBLIC OF", "Korea, Democratic People's Republic of"), KR("KR",
		                                                                                                                                                                                                                                        "KOREA, REPUBLIC OF",
		                                                                                                                                                                                                                                        "South Korea"), KW(
				"KW", "KUWAIT", "Kuwait"), KG("KG", "KYRGYZSTAN", "Kyrgyzstan"), LA("LA", "LAO PEOPLE'S DEMOCRATIC REPUBLIC", "Lao People's Democratic Republic"), LV("LV", "LATVIA", "Latvia"), LB("LB", "LEBANON", "Lebanon"), LS("LS", "LESOTHO",
		                                                                                                                                                                                                                            "Lesotho"), LR("LR",
		                                                                                                                                                                                                                                           "LIBERIA",
		                                                                                                                                                                                                                                           "Liberia"), LY(
				"LY", "LIBYA", "Libyan Arab Jamahiriya"), LI("LI", "LIECHTENSTEIN", "Liechtenstein"), LT("LT", "LITHUANIA", "Lithuania"), LU("LU", "LUXEMBOURG", "Luxembourg"), MO("MO", "MACAO", "Macao"), MK("MK",
		                                                                                                                                                                                                       "MACEDONIA, THE FORMER YUGOSLAV REPUBLIC OF",
		                                                                                                                                                                                                       "Macedonia"), MG("MG", "MADAGASCAR",
		                                                                                                                                                                                                                        "Madagascar"), MW("MW",
		                                                                                                                                                                                                                                          "MALAWI",
		                                                                                                                                                                                                                                          "Malawi"), MY(
				"MY", "MALAYSIA", "Malaysia"), MV("MV", "MALDIVES", "Maldives"), ML("ML", "MALI", "Mali"), MT("MT", "MALTA", "Malta"), MH("MH", "MARSHALL ISLANDS", "Marshall Islands"), MQ("MQ", "MARTINIQUE", "Martinique"), MR("MR", "MAURITANIA",
		                                                                                                                                                                                                                          "Mauritania"), MU("MU",
		                                                                                                                                                                                                                                            "MAURITIUS",
		                                                                                                                                                                                                                                            "Mauritius"), YT(
				"YT", "MAYOTTE", "Mayotte"), MX("MX", "MEXICO", "Mexico"), FM("FM", "MICRONESIA, FEDERATED STATES OF", "Micronesia, Federated States of"), MD("MD", "MOLDOVA, REPUBLIC OF", "Moldova, Republic of"), MC("MC", "MONACO", "Monaco"), MN(
				"MN", "MONGOLIA", "Mongolia"), ME("ME", "MONTENEGRO", "Montenegro"), MS("MS", "MONTSERRAT", "Montserrat"), MA("MA", "MOROCCO", "Morocco"), MZ("MZ", "MOZAMBIQUE", "Mozambique"), MM("MM", "MYANMAR", "Myanmar"), NA("NA", "NAMIBIA",
		                                                                                                                                                                                                                            "Namibia"), NR("NR",
		                                                                                                                                                                                                                                           "NAURU",
		                                                                                                                                                                                                                                           "Nauru"), NP(
				"NP", "NEPAL", "Nepal"), NL("NL", "NETHERLANDS", "Netherlands"), NC("NC", "NEW CALEDONIA", "New Caledonia"), NZ("NZ", "NEW ZEALAND", "New Zealand"), NI("NI", "NICARAGUA", "Nicaragua"), NE("NE", "NIGER", "Niger"), NG("NG", "NIGERIA",
		                                                                                                                                                                                                                                "Nigeria"), NU(
				"NU", "NIUE", "Niue"), NF("NF", "NORFOLK ISLAND", "Norfolk Island"), MP("MP", "NORTHERN MARIANA ISLANDS", "Northern Mariana Islands"), NO("NO", "NORWAY", "Norway"), OM("OM", "OMAN", "Oman"), PK("PK", "PAKISTAN", "Pakistan"), PW("PW",
		                                                                                                                                                                                                                                            "PALAU",
		                                                                                                                                                                                                                                            "Palau"), PS(
				"PS", "PALESTINE, STATE OF", "Palestinian Territory"), PA("PA", "PANAMA", "Panama"), PG("PG", "PAPUA NEW GUINEA", "Papua New Guinea"), PY("PY", "PARAGUAY", "Paraguay"), PE("PE", "PERU", "Peru"), PH("PH", "PHILIPPINES",
		                                                                                                                                                                                                              "Philippines"), PN("PN", "PITCAIRN",
		                                                                                                                                                                                                                                 "Pitcairn"), PL(
				"PL", "POLAND", "Poland"), PT("PT", "PORTUGAL", "Portugal"), PR("PR", "PUERTO RICO", "Puerto Rico"), QA("QA", "QATAR", "Qatar"), RE("RE", "RÉUNION", "Reunion"), RO("RO", "ROMANIA", "Romania"), RU("RU", "RUSSIAN FEDERATION",
		                                                                                                                                                                                                            "Russia"), RW("RW", "RWANDA",
		                                                                                                                                                                                                                          "Rwanda"), BL("BL",
		                                                                                                                                                                                                                                        "SAINT BARTHÉLEMY",
		                                                                                                                                                                                                                                        "Saint Bartelemey"), SH(
				"SH", "SAINT HELENA, ASCENSION AND TRISTAN DA CUNHA", "Saint Helena"), KN("KN", "SAINT KITTS AND NEVIS", "Saint Kitts and Nevis"), LC("LC", "SAINT LUCIA", "Saint Lucia"), MF("MF", "SAINT MARTIN (FRENCH PART)", "Saint Martin"), PM(
				"PM", "SAINT PIERRE AND MIQUELON", "Saint Pierre and Miquelon"), VC("VC", "SAINT VINCENT AND THE GRENADINES", "Saint Vincent and the Grenadines"), WS("WS", "SAMOA", "Samoa"), SM("SM", "SAN MARINO", "San Marino"), ST("ST",
		                                                                                                                                                                                                                                "SAO TOME AND PRINCIPE",
		                                                                                                                                                                                                                                "Sao Tome and Principe"), SA(
				"SA", "SAUDI ARABIA", "Saudi Arabia"), SN("SN", "SENEGAL", "Senegal"), RS("RS", "SERBIA", "Serbia"), SC("SC", "SEYCHELLES", "Seychelles"), SL("SL", "SIERRA LEONE", "Sierra Leone"), SG("SG", "SINGAPORE", "Singapore"), SX("SX",
		                                                                                                                                                                                                                                    "SINT MAARTEN (DUTCH PART)",
		                                                                                                                                                                                                                                    "Sint Maarten"), SK(
				"SK", "SLOVAKIA", "Slovakia"), SI("SI", "SLOVENIA", "Slovenia"), SB("SB", "SOLOMON ISLANDS", "Solomon Islands"), SO("SO", "SOMALIA", "Somalia"), ZA("ZA", "SOUTH AFRICA", "South Africa"), GS("GS",
		                                                                                                                                                                                                      "SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS",
		                                                                                                                                                                                                      "South Georgia and the South Sandwich Islands"), SS(
				"SS", "SOUTH SUDAN", "South Sudan"), ES("ES", "SPAIN", "Spain"), LK("LK", "SRI LANKA", "Sri Lanka"), SD("SD", "SUDAN", "Sudan"), SR("SR", "SURINAME", "Suriname"), SJ("SJ", "SVALBARD AND JAN MAYEN", "Svalbard and Jan Mayen"), SZ("SZ",
		                                                                                                                                                                                                                                            "SWAZILAND",
		                                                                                                                                                                                                                                            "Swaziland"), SE(
				"SE", "SWEDEN", "Sweden"), CH("CH", "SWITZERLAND", "Switzerland"), SY("SY", "SYRIAN ARAB REPUBLIC", "Syria"), TW("TW", "TAIWAN, PROVINCE OF CHINA", "Taiwan"), TJ("TJ", "TAJIKISTAN", "Tajikistan"), TZ("TZ",
		                                                                                                                                                                                                                "TANZANIA, UNITED REPUBLIC OF",
		                                                                                                                                                                                                                "Tanzania, United Republic of"), TH(
				"TH", "THAILAND", "Thailand"), TL("TL", "TIMOR-LESTE", "Timor-Leste"), TG("TG", "TOGO", "Togo"), TK("TK", "TOKELAU", "Tokelau"), TO("TO", "TONGA", "Tonga"), TT("TT", "TRINIDAD AND TOBAGO", "Trinidad and Tobago"), TN("TN", "TUNISIA",
		                                                                                                                                                                                                                                "Tunisia"), TR(
				"TR", "TURKEY", "Turkey"), TM("TM", "TURKMENISTAN", "Turkmenistan"), TC("TC", "TURKS AND CAICOS ISLANDS", "Turks and Caicos Islands"), TV("TV", "TUVALU", "Tuvalu"), UG("UG", "UGANDA", "Uganda"), UA("UA", "UKRAINE", "Ukraine"), AE(
				"AE", "UNITED ARAB EMIRATES", "United Arab Emirates"), GB("GB", "UNITED KINGDOM", "United Kingdom"), US("US", "UNITED STATES", "United States"), UM("UM", "UNITED STATES MINOR OUTLYING ISLANDS",
		                                                                                                                                                            "United States Minor Outlying Islands"), UY("UY", "URUGUAY", "Uruguay"), UZ("UZ",
		                                                                                                                                                                                                                                        "UZBEKISTAN",
		                                                                                                                                                                                                                                        "Uzbekistan"), VU(
				"VU", "VANUATU", "Vanuatu"), VE("VE", "VENEZUELA, BOLIVARIAN REPUBLIC OF", "Venezuela"), VN("VN", "VIET NAM", "Vietnam"), VG("VG", "VIRGIN ISLANDS, BRITISH", "Virgin Islands, British"), VI("VI", "VIRGIN ISLANDS, U.S.",
		                                                                                                                                                                                                     "Virgin Islands, U.S."), WF("WF",
		                                                                                                                                                                                                                                 "WALLIS AND FUTUNA",
		                                                                                                                                                                                                                                 "Wallis and Futuna"), EH(
				"EH", "WESTERN SAHARA", "Western Sahara"), YE("YE", "YEMEN", "Yemen"), ZM("ZM", "ZAMBIA", "Zambia"), ZW("ZW", "ZIMBABWE", "Zimbabwe");
		private static Map<String, CountryCode> names = new HashMap<>();
		private static Map<String, String> countryNames;
		private static Map<String, String> softCountryNames;
		private final String code;
		private final String country;
		private final String softCountry;

		CountryCode(final String code, final String country, final String softCountry) {

			this.code = code;
			this.country = country;
			this.softCountry = softCountry;
			addCountry(country, softCountry, code);
		}

		static {
			names = EnumUtils.createNames(values());
			softCountryNames.put("Republic of Lithuania", CountryCode.LT.code);
			softCountryNames.put("Slovak Republic", CountryCode.SK.code);
			softCountryNames.put("Republic of Korea", CountryCode.KR.code);
			softCountryNames.put("Iran", CountryCode.IR.code);
			softCountryNames.put("Republic of Moldova", CountryCode.MD.code);
		}

		private static void addCountry(final String country, final String softCountry, final String code) {

			if (countryNames == null) {
				countryNames = new HashMap<>();
			}
			if (softCountryNames == null) {
				softCountryNames = new HashMap<>();
			}
			countryNames.put(country, code);
			softCountryNames.put(softCountry, code);
		}

		public String getCountry() {

			return country;
		}

		public String getSoftCountry() {

			return softCountry;
		}

		@JsonValue
		@Override
		public String toString() {

			return code;
		}

		@JsonCreator
		public static CountryCode fromString(final String name) {

			return EnumUtils.fromString(names, name == null ? name : name.toUpperCase());
		}

		public static String getCodeFromCountryName(final String name) {

			try {
				String countryName = name.trim().toUpperCase();
				if (!countryNames.containsKey(countryName)) {
					if (!softCountryNames.containsKey(name.trim())) {
						throw new NullPointerException("Not in softCountryNames.");
					}
					return softCountryNames.get(name.trim());
				}
				return countryNames.get(countryName);
			} catch (Exception e) {
				throw new IllegalArgumentException("Country name not recognised. Value was " + name != null ? name : "[null]" + ".", e);
			}
		}
	}

	private String extension;
	private String street;
	private String postalCode;
	private String city;
	private String county;
	private String state;
	private String country;
	private CountryCode countryCode;
	private String district;
	private String buildingName;
	private String buildingFloor;
	private String buildingRoom;
	private String buildingZone;
	private String crossing1;
	private String crossing2;

	public AddressInfo() {

	}

	public AddressInfo(final String extension, final String street, final String postalCode, final String city, final String county, final String state, final String country, final CountryCode countryCode, final String district,
	                   final String buildingName, final String buildingFloor, final String buildingRoom, final String buildingZone, final String crossing1, final String crossing2) {

		this.extension = extension;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
		this.county = county;
		this.state = state;
		this.country = country;
		this.countryCode = countryCode;
		this.district = district;
		this.buildingName = buildingName;
		this.buildingFloor = buildingFloor;
		this.buildingRoom = buildingRoom;
		this.buildingZone = buildingZone;
		this.crossing1 = crossing1;
		this.crossing2 = crossing2;
	}

	public String getExtension() {

		return extension;
	}

	public void setExtension(final String extension) {

		this.extension = extension;
	}

	public String getStreet() {

		return street;
	}

	public void setStreet(final String street) {

		this.street = street;
	}

	public String getPostalCode() {

		return postalCode;
	}

	public void setPostalCode(final String postalCode) {

		this.postalCode = postalCode;
	}

	public String getCity() {

		return city;
	}

	public void setCity(final String city) {

		this.city = city;
	}

	public String getCounty() {

		return county;
	}

	public void setCounty(final String county) {

		this.county = county;
	}

	public String getState() {

		return state;
	}

	public void setState(final String state) {

		this.state = state;
	}

	public String getCountry() {

		return country;
	}

	public void setCountry(final String country) {

		this.country = country;
	}

	public CountryCode getCountryCode() {

		return countryCode;
	}

	public void setCountryCode(final CountryCode countryCode) {

		this.countryCode = countryCode;
	}

	public String getDistrict() {

		return district;
	}

	public void setDistrict(final String district) {

		this.district = district;
	}

	public String getBuildingName() {

		return buildingName;
	}

	public void setBuildingName(final String buildingName) {

		this.buildingName = buildingName;
	}

	public String getBuildingFloor() {

		return buildingFloor;
	}

	public void setBuildingFloor(final String buildingFloor) {

		this.buildingFloor = buildingFloor;
	}

	public String getBuildingRoom() {

		return buildingRoom;
	}

	public void setBuildingRoom(final String buildingRoom) {

		this.buildingRoom = buildingRoom;
	}

	public String getBuildingZone() {

		return buildingZone;
	}

	public void setBuildingZone(final String buildingZone) {

		this.buildingZone = buildingZone;
	}

	public String getCrossing1() {

		return crossing1;
	}

	public void setCrossing1(final String crossing1) {

		this.crossing1 = crossing1;
	}

	public String getCrossing2() {

		return crossing2;
	}

	public void setCrossing2(final String crossing2) {

		this.crossing2 = crossing2;
	}
}
