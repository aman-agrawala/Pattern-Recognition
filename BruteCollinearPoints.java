import java.util.ArrayList;
import java.util.Arrays;
import java.lang.NullPointerException;

public class BruteCollinearPoints 
{
   private LineSegment[] segments;
   
   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   {
       if(points == null)
       {
           throw new NullPointerException("Input cannot be null");
       }
       
       duplicated(points); // check for duplicates and null values within array
       
       ArrayList<LineSegment> foundSegments = new ArrayList<LineSegment>();
       
       Point[] pointsCopy = Arrays.copyOf(points, points.length);
       Arrays.sort(pointsCopy);
       
       for(int p = 0; p < points.length -3; p++)
       {
           for(int q = p+1; q <points.length - 2; q++)
           {
               for(int r = q+1; r < points.length -1; r++)
               {
                   for(int s = r+1; s < points.length; s++)
                   {
                       if (pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[r]) &&
                                pointsCopy[p].slopeTo(pointsCopy[q]) == pointsCopy[p].slopeTo(pointsCopy[s])) 
                       {
                           foundSegments.add(new LineSegment(pointsCopy[p], pointsCopy[s]));
                       }
                   }
               }
           }
       }
       segments = foundSegments.toArray(new LineSegment[foundSegments.size()]);
   }
   
   public int numberOfSegments()        // the number of line segments
   {
       return segments.length;
   }
   
   public LineSegment[] segments()                // the line segments
   {
       return Arrays.copyOf(segments, numberOfSegments());
   }
   
   private void duplicated(Point[] points)
   {
       for(int i = 0; i<points.length-1; i++)
       {
           for(int j = i+1; j<points.length; j++)
           {
               if(points[i] == null || points[j] == null)
               {
                   throw new NullPointerException("Input cannot contain a null value");
               }
               if(points[i].compareTo(points[j]) == 0)
               {
                   throw new IllegalArgumentException("Duplicated entries in given points.");
               }
           }
       }   
   }
}