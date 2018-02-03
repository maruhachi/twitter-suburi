package me.ykon.twittersuburi.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.oauth1.OAuth1Operations;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;
import org.springframework.social.twitter.connect.TwitterConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

/**
 * @author ykonno-server
 * @since 2018/01/27
 */
@Controller
public class TwitterOAuth {

    @Value("${twitter.consumer.key}")
    private String CONSUMER_KEY = "";

    @Value("${twitter.consumer.secret}")
    private String CONSUMER_SECRET = "";

    @Value("${twitter.callback.url}")
    private String CALLBACK_URL = "";

    @GetMapping("/step1")
    public ModelAndView step1(){

        TwitterConnectionFactory factory = new TwitterConnectionFactory(CONSUMER_KEY, CONSUMER_SECRET);


        OAuth1Operations oAuthOperations = factory.getOAuthOperations();
        OAuthToken token = oAuthOperations.fetchRequestToken(CALLBACK_URL, null);

        String authorizeUrl = oAuthOperations.buildAuthorizeUrl(token.getValue(), OAuth1Parameters.NONE);

        return new ModelAndView("redirect:" + authorizeUrl, new HashMap<String, Object>());
    }
}
