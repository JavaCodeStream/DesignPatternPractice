package edu.javacodestream.designpattern.structural;

/**
 * Facade is a structural design pattern that provides a simplified interface to a
 * library, a framework, or any other complex set of classes.
 *
 * A facade might provide limited functionality in comparison to working with the subsystem directly.
 * However, it includes only those features that clients really care about.
 *
 * Having a facade is handy when you need to integrate your app with a sophisticated
 * library that has dozens of features, but you just need a tiny bit of its functionality.
 *
 * In this example, the Facade pattern simplifies interaction with a complex video conversion framework.
 *
 * Instead of making your code work with dozens of the framework classes directly, you create a
 * facade class which encapsulates that functionality and hides it from the rest of the code.
 * This structure also helps you to minimize the effort of upgrading to future versions of the
 * framework or replacing it with another one. The only thing you’d need to change in your app
 * would be the implementation of the facade’s methods.
 *
 * Usage:
 * Use the Facade pattern when you need to have a limited but straightforward interface
 * to a complex subsystem.
 *
 */

// These are some of the classes of a complex 3rd-party video
// conversion framework. We don't control that code, therefore
// can't simplify it.

/*class VideoFile
// ...

class OggCompressionCodec
// ...

class MPEG4CompressionCodec
// ...

class CodecFactory
// ...

class BitrateReader
// ...

class AudioMixer
// ...


// We create a facade class to hide the framework's complexity
// behind a simple interface. It's a trade-off between
// functionality and simplicity.
class VideoConverter is
        method convert(filename, format):File is
        file = new VideoFile(filename)
        sourceCodec = new CodecFactory.extract(file)
        if (format == "mp4")
        destinationCodec = new MPEG4CompressionCodec()
        else
        destinationCodec = new OggCompressionCodec()
        buffer = BitrateReader.read(filename, sourceCodec)
        result = BitrateReader.convert(buffer, destinationCodec)
        result = (new AudioMixer()).fix(result)
        return new File(result)

// Application classes don't depend on a billion classes
// provided by the complex framework. Also, if you decide to
// switch frameworks, you only need to rewrite the facade class.
class Application is
        method main() is
        convertor = new VideoConverter()
        mp4 = convertor.convert("funny-cats-video.ogg", "mp4")
        mp4.save()

*/


public class FacadeDemo {
}
