import java.util.*;
import java.lang.Math.*;

public class FastCollinearPoints 
{
    private LineSegment[] segments;
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        if(points == null)
        {
            throw new NullPointerException("Input cannot be null");
        }
        
        duplicated(points);
        
        ArrayList<LineSegment> foundSegments = new ArrayList<LineSegment>();
        Point[] pointsCopy = Arrays.copyOf(points, points.length);
        
        int slopeCount = 0;
        HashMap<Double, Point> matchingSlope = new HashMap<Double, Point>();
        double prevSlope = 0;
        double currentSlope = 0;
        
        for(Point startingPoint : points)
        {
            Arrays.sort(pointsCopy,startingPoint.slopeOrder());
            
            for(int i = 0; i <points.length; i++)
            {
                currentSlope = startingPoint.slopeTo(pointsCopy[i]);
                if(i != 0)
                {
                    if(prevSlope == currentSlope)
                    {
                        slopeCount++;
                        matchingSlope.put(distance(startingPoint,pointsCopy[i]),pointsCopy[i]);
                    }
                }
                prevSlope = startingPoint.slopeTo(pointsCopy[i]);
                
                
            }
        }
        
    }
    
    
    public int numberOfSegments()        // the number of line segments
    {
        return 0;
    }
    
    
    public LineSegment[] segments()                // the line segments
    {
        return new LineSegment[1];
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
    
    private double distance(Point p1, Point p2)
    {
        return p1.distance(p2);
    }
}