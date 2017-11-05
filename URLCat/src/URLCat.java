import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class URLCat
{
    static
    {
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }
    
    public static void main(String[] a)
    {
        InputStream in = null;
        try
        {
            in = new URL(a[0]).openStream();
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            IOUtils.closeStream(in);
        }
    }
}
