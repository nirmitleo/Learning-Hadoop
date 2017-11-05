import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

public class FileSystemCat
{
    public static void main(String[] a)
    {
        FileSystem fs = null;
        InputStream in = null;
        
        String uri = a[0];
        Configuration conf = new Configuration();
        try
        {
            fs = FileSystem.get(URI.create(uri), conf);
            in = fs.open(new Path(a[0]));
            IOUtils.copyBytes(in, System.out, 4096, false);
        } catch (IOException e)
        {
            e.printStackTrace();
        } finally
        {
            IOUtils.closeStream(in);
        }
    }
}
