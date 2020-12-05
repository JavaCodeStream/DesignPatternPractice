package edu.javacodestream.designpattern.structural;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Ref# https://refactoring.guru/design-patterns/proxy
 * Proxy is a structural design pattern that lets you provide a substitute or placeholder
 * for another object. A proxy controls access to the original object, allowing you to
 * perform something either before or after the request gets through to the original object.
 *
 * This example illustrates how the Proxy pattern can help to introduce lazy initialization
 * and caching to a 3rd-party YouTube integration library.
 *
 * The library provides us with the video downloading class. However, it’s very inefficient.
 * If the client application requests the same video multiple times, the library just downloads
 * it over and over, instead of caching and reusing the first downloaded file.
 *
 * The proxy class implements the same interface as the original downloader and delegates
 * it all the work. However, it keeps track of the downloaded files and returns the cached
 * result when the app requests the same video multiple times.
 */

interface ThirdPartyYouTubeLib {
    void listVideos();
    void getVideoInfo(int id);
    void downloadVideo(int id);
}

// The concrete implementation of a service connector. Methods
// of this class can request information from YouTube. The speed
// of the request depends on a user's internet connection as
// well as YouTube's. The application will slow down if a lot of
// requests are fired at the same time, even if they all request
// the same information.
class ThirdPartyYouTubeClass implements ThirdPartyYouTubeLib {

    public ThirdPartyYouTubeClass() {
        System.out.println("Constructing the ThirdPartyYouTubeClass...");
    }

    @Override
    public void listVideos() {
        // Send an API request to YouTube.
        System.out.println("Listing Videos directly from YouTube...");
    }

    @Override
    public void getVideoInfo(int id) {
        // Get metadata about some video.
        System.out.println("Extracting the video metadata info for videoId: "
                + id + " - directly from YouTube...");
    }

    @Override
    public void downloadVideo(int id) {
        // Download a video file from YouTube.
        System.out.println("Downloading the video for videoId: "
                + id + " - directly from YouTube...");
    }
}

class CachedYouTubeClassProxy implements ThirdPartyYouTubeLib {

    ThirdPartyYouTubeClass thirdPartyYouTubeClass;

    Set<Integer> videoIdsMetadataCache = Sets.newHashSet();
    Set<Integer> videoIdsDataCache = Sets.newHashSet();

    public CachedYouTubeClassProxy() {
        System.out.println("Constructing the CachedYouTubeClassProxy");
        thirdPartyYouTubeClass = new ThirdPartyYouTubeClass();
    }

    @Override
    public void listVideos() {
        System.out.println("Invoked listVideos() of CachedYouTubeClassProxy..");
        thirdPartyYouTubeClass.listVideos();
    }

    @Override
    public void getVideoInfo(int id) {
        System.out.println("Invoked getVideoInfo() of CachedYouTubeClassProxy...");
        if (videoIdsMetadataCache.contains(id)) {
            System.out.println("Extracting the video metadata info for videoId: "
                    + id + " - from YouTube metadata local Cache...");
        } else {
            thirdPartyYouTubeClass.getVideoInfo(id);
            videoIdsMetadataCache.add(id);
        }
    }

    @Override
    public void downloadVideo(int id) {
        System.out.println("Invoked downloadVideo() of CachedYouTubeClassProxy...");
        if (videoIdsDataCache.contains(id)) {
            System.out.println("Downloading the video for videoId: "
                    + id + " - from YouTube Data local Cache...");
        } else {
            thirdPartyYouTubeClass.downloadVideo(id);
            videoIdsDataCache.add(id);
        }
    }
}

public class ProxyDemo {
    public static void main(String[] args) {
        System.out.println("Proxy Demo test...");

        CachedYouTubeClassProxy cachedYouTubeClassProxy = new CachedYouTubeClassProxy();

        cachedYouTubeClassProxy.listVideos();

        cachedYouTubeClassProxy.getVideoInfo(10);
        cachedYouTubeClassProxy.getVideoInfo(10);

        cachedYouTubeClassProxy.downloadVideo(20);
        cachedYouTubeClassProxy.downloadVideo(20);

    }
}
