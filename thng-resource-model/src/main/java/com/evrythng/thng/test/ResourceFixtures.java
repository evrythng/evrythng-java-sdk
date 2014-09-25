package com.evrythng.thng.test;

import com.evrythng.thng.resource.model.store.SocialNetwork;
import com.evrythng.thng.resource.model.store.User;

import java.util.*;

public final class ResourceFixtures {

    private static final Random random = new Random();

    public static final class Users {

        public static final String FIRST_NAME = "Mario";
        public static final String LAST_NAME = "Bros";
        public static final User.Birthday BIRTHDAY = new User.Birthday(21, 4, 1990);
        public static final User.Gender GENDER = User.Gender.MALE;
        public static final String LOCALE = Locale.UK.getCountry();
        public static final Integer NUMBER_OF_FRIENDS = 1000;
        public static final SocialNetwork PRIMARY_SOCIAL_NETWORK = SocialNetwork.FACEBOOK;
        public static final Map<String, String> CUSTOM_FIELDS = new HashMap<>();
        public static final List<String> TAGS = new ArrayList<>();

        public static class Builder {

            private String email;
            private String password;
            private String firstName;
            private String lastName;

            private User.Birthday birthday;
            private User.Gender gender;
            private String locale;
            private Integer numberOfFriends;
            private String photo;
            private SocialNetwork primarySocialNetwork;
            private Long socialProfileLastSync;
            private String timezone;
            private Map<String, String> customFields;
            private Long createdAt;
            private String id;
            private List<String> tags;
            private Long updatedAt;

            public Builder(final String email, final String password, final String firstName, final String lastName) {
                this.email = email;
                this.password = password;
                this.firstName = firstName;
                this.lastName = lastName;
            }

            public com.evrythng.thng.resource.model.store.User build() {

                User user = new User();
                user.setEmail(email);
                user.setPassword(password);
                user.setFirstName(firstName);
                user.setLastName(lastName);

                user.setBirthday(birthday);
                user.setGender(gender);
                user.setLocale(locale);
                user.setNumberOfFriends(numberOfFriends);
                user.setPhoto(photo);
                user.setPrimarySocialNetwork(primarySocialNetwork);
                user.setSocialProfileLastSync(socialProfileLastSync);
                user.setTimezone(timezone);
                user.setCustomFields(customFields);
                user.setCreatedAt(createdAt);
                user.setId(id);
                user.setTags(tags);
                user.setUpdatedAt(updatedAt);
                return user;
            }

            public Builder withEmail(final String email) {
                this.email = email;
                return this;
            }

            public Builder withPassword(String password) {
                this.password = password;
                return this;
            }

            public Builder withFirstName(String firstName) {
                this.firstName = firstName;
                return this;
            }

            public Builder withLastName(String lastName) {
                this.lastName = lastName;
                return this;
            }

            public Builder withBirthday(User.Birthday birthday) {
                this.birthday = birthday;
                return this;
            }

            public Builder withGender(User.Gender gender) {
                this.gender = gender;
                return this;
            }

            public Builder withLocale(String locale) {
                this.locale = locale;
                return this;
            }

            public Builder withNumberOfFriends(Integer numberOfFriends) {
                this.numberOfFriends = numberOfFriends;
                return this;
            }

            public Builder withPhoto(String photo) {
                this.photo = photo;
                return this;
            }

            public Builder withPrimarySocialNetwork(SocialNetwork primarySocialNetwork) {
                this.primarySocialNetwork = primarySocialNetwork;
                return this;
            }

            public Builder withSocialProfileLastSync(Long socialProfileLastSync) {
                this.socialProfileLastSync = socialProfileLastSync;
                return this;
            }

            public Builder withTimezone(String timezone) {
                this.timezone = timezone;
                return this;
            }

            public Builder withCustomFields(Map<String, String> customFields) {
                this.customFields = customFields;
                return this;
            }

            public Builder withCreatedAt(Long createdAt) {
                this.createdAt = createdAt;
                return this;
            }

            public Builder withId(String id) {
                this.id = id;
                return this;
            }

            public Builder withTags(List<String> tags) {
                this.tags = tags;
                return this;
            }

            public Builder withUpdatedAt(Long updatedAt) {
                this.updatedAt = updatedAt;
                return this;
            }
        }

        public static Builder get(){

            Builder builder = new Builder(UUID.randomUUID().toString() + "@evrythng.com", "thng!@#$%" + randomInt(0, 255), FIRST_NAME, LAST_NAME);
            builder.withBirthday(BIRTHDAY);
            builder.withGender(GENDER);
            builder.withLocale(LOCALE);
            builder.withNumberOfFriends(NUMBER_OF_FRIENDS);
            builder.withPrimarySocialNetwork(PRIMARY_SOCIAL_NETWORK);
            builder.withCustomFields(CUSTOM_FIELDS);
            builder.withTags(TAGS);
            return builder;
        }

        private Users() {
            // prevents instantiation
        }
    }
    private ResourceFixtures() {
        // prevents instantiation
    }

    private static int randomInt(int min, int max) {

        return random.nextInt(max - min + 1) + min;
    }
}
