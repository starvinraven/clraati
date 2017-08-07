(ns clraati.facebook
  (:import [org.scribe.oauth OAuthService]
           [org.scribe.builder ServiceBuilder]
           [org.scribe.model Token Verifier OAuthRequest Verb]
           [org.scribe.builder.api FacebookApi]))

(def facebook-service
  (-> (doto
        (ServiceBuilder.)
        (.provider (FacebookApi.))
        (.apiKey (conf/get-cfg :facebook-api-key))
        (.apiSecret (conf/get-cfg :facebook-api-secret))
        (.callback (conf/get-cfg :facebook-callback-url)))
      (.scope "email")
      (.build)))

(def facebook-url
  (.getAuthorizationUrl facebook-service nil))

(defn get-info-facebook [code]
                        (let [verifier (Verifier. code)
                              token (.getAccessToken facebook-service nil verifier)
                              request (OAuthRequest. (Verb/GET) "https://graph.facebook.com/me")]
                          (.signRequest facebook-service token request)
                          (.getBody (.send request))))

(defn create-user-facebook
  [user id]
  {:fb-user         user
   :name            (:name user)
   :username        (:username user)
   :email           (or (:email user) (str (:username user) "@facebook.com"))
   :last-login-time nil})