import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

public class FileSystemDoubleCat
{
    public static void main(String[] a)
    {
        String uri = a[0];
        Configuration conf = new Configuration();
        
        FileSystem fs = null;
        FSDataInputStream in = null;
        
        try
        {
            fs = FileSystem.get(URI.create(uri), conf);
            in = fs.open(new Path(uri));
            
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0);
            
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
