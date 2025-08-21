package dev.lpa;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class URIBasics {


    public static void main(String[] args) {

        //Base URI, which will be used in conjunction with relative URIs
        //Whenever I need to use a relative URI as URL, we can resolve the base/absolute URI
        URI baseSite = URI.create("https://learnprogramming.academy");

        //We create a URI variable. There are two ways to do this
        //1. The create method (doesn't throw exceptions)
//        URI timsSite = URI.create("https://learnprogramming.academy/");
        URI timsSite = URI.create("courses/complete-java-masterclass");
        print(timsSite);

        //2. Using the constructor

        //We pass: the user and password,
        // specifying at (@) store.com
        // followed by the port
        // and a path,
        // a query
        // and a fragment

        try {
            URI uri = new URI("http://user:pw@store.com:5000/products/phones?os=android#samsung"); //fake website
            print(uri);

            URI masterClass = baseSite.resolve(timsSite); //timsSite holds a relative URI

            //Turning a URI to a URL
            URL url = masterClass.toURL();
            System.out.println(url);
            print(url);
        } catch (URISyntaxException | MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    //We will create a private method that will print the component parts in the hierarchy
    private static void print (URI uri) {

        System.out.printf("""                
                --------------------------------------------------
                [scheme:]scheme-specific-part[#fragment]
                --------------------------------------------------
                Scheme: %s
                Scheme-specific-part: %s
                    Authority: %s
                        User-Info: %s
                        Host: %s
                        Port: %d
                        Path: %s
                        Query: %s
                Fragment: %s
                """,
                uri.getScheme(),
                uri.getSchemeSpecificPart(),
                uri.getAuthority(),
                uri.getUserInfo(),
                uri.getHost(),
                uri.getPort(),
                uri.getPath(),
                uri.getQuery(),
                uri.getFragment()
                );
    }

    private static void print (URL url) {

        System.out.printf("""    
                --------------------------------------------------
                    Authority: %s
                        User-Info: %s
                        Host: %s
                        Port: %d
                        Path: %s
                        Query: %s
                """,
                url.getAuthority(),
                url.getUserInfo(),
                url.getHost(),
                url.getPort(),
                url.getPath(),
                url.getQuery()
        );
    }
}
