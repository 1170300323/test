package P2.turtle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;

public class TurtleSoup {

    private static final double INF = 1000000000;

	/**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	for(int i = 1; i <= 4; i++) {
    		turtle.forward(sideLength);
        	turtle.turn(90);
    	}
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
        return (180.0 * (sides - 2)) / sides;
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	return 360 / (int)(180 - angle);
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	int a = 180 * (sides - 2) / sides;
        turtle.turn(90 - a);
        for(int i = 1; i <= sides; i++) {
        	turtle.forward(sideLength);
        	turtle.turn(180 - a);
        }
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double b;
    	if(currentX == targetX) {
    		if(currentY > targetY)
    			b =  180 - currentBearing;
    		else
    			b =  360 - currentBearing;
    	}
    	else {
    		double a0 = Math.atan((currentY - targetY) / (currentX - targetX));
    		double a = 180 * a0 / Math.PI;
    		if(currentY > targetY)
    			b =  270 - a - currentBearing;
    		else
    			b =  90 - a - currentBearing;
    	}
    	if(b < 0)
    		b = 360 + b;
    	if(b >= 360)
    		b -= 360;
    	return b;
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        List<Double> lists =  new ArrayList<Double>();
        int a, b, c = xCoords.get(0), d = yCoords.get(0), len = xCoords.size();
        double cb = 0, angle;
        for(int i = 1; i < len; i++) {
        	a = c;
        	b = d;
        	c = xCoords.get(i);
        	d = yCoords.get(i);
        	angle = calculateBearingToPoint(cb, a, b, c, d);
        	lists.add(angle);
        	cb += angle;
        	if(cb >= 360) {
        		cb -= 360;
        	}
        }
        return lists;
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
       Set<Point> set = new HashSet<Point>();
       Point s0 = new Point(0, 0);
       boolean flag = true;
       if(points.isEmpty()) {
    	   return Collections.emptySet();
       }
       if(points.size() == 1 || points.size() == 2)
    	   return points;
       for(Point pw : points) {
    	   if(flag) {
    		   s0 = new Point(pw.x(), pw.y());
    		   flag = false;
    	   }
    	   if(pw.x() < s0.x())
    		   s0 = pw;
    	   else if(pw.x() == s0.x() && pw.y() < s0.y())
    		   s0 = pw;
       }
       set.add(s0);
       int i = 0;
       Point p2 = s0;
       flag = true;
       double k, l;
       for(Point pw : points) {
    	   if(pw.x() == s0.x() && pw.y() > s0.y()) {
    		   k = INF;
    		   p2 = pw;
    	   }
    	   else if(pw.x() == s0.x()) {
    		   k = -INF;
    	   }
    	   else
    		   k = (pw.y() - s0.y()) / (pw.x() - s0.x());
    	   if(flag) {
    		   p2 = pw;
    		   flag = false;
    	   }
    	   if(p2.x() == s0.x() && p2.y() > s0.y())
    		   l = INF;
    	   else if(p2.x() == s0.x())
    		   l = -INF;
    	   else
    		   l = (p2.y() - s0.y()) / (p2.x() - s0.x());
    	   if(k > l)
    		   p2 = pw;
    	   else if(k == l && pw.y() > p2.y())
    		   p2 = pw;
       }
       set.add(p2);
       double min = 0;
       System.out.println(p2.x());
       System.out.println(p2.y());
       Point p3 = p2, p1 = s0;
       while(p3 != s0) {
    	   flag = true;
    	   for(Point pw : points) {
    		   if(pw.x() == p1.x() && pw.y() == p1.y())
    			   continue;
    		   if(pw.x() == p2.x() && pw.y() == p2.y())
    			   continue;
        	   double p = (p1.x() - p2.x()) * (pw.x() - p2.x());
        	   double q = (p1.y() - p2.y()) * (pw.y() - p2.y());
        	   double r = Math.sqrt((pw.x() - p2.x()) * (pw.x() - p2.x()) + (pw.y() - p2.y()) * (pw.y() - p2.y()));
        	   double t = (p + q) / r;
        	   if(flag) {
        		   min = t;
        		   flag = false;
        	   }
        	   if(t <= min) {
        		   min = t;
        		   p3 = pw;
        	   }
           }
    	   p1 = p2;
    	   p2 = p3;
    	   set.add(p3);
       }
       return set;
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	turtle.color(PenColor.ORANGE);
    	int t = 0;
    	while(t < 50) {//50,75,100
    		turtle.turn(142.3);//142.3,171.3,46.7,80.3,105.7
    		turtle.forward(100);
    		t++;
    	}
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        drawPersonalArt(turtle);
        //drawSquare(turtle, 40);
        //drawRegularPolygon(turtle, 3, 40);
        // draw the window
        turtle.draw();
    	Set<Point> points = new HashSet<Point>();
    	Point p11 = new Point(1, 1);
		Point p1010 = new Point(10, 10);
		Point p110 = new Point(1, 10);
		Point p12 = new Point(1, 2);
		Point p23 = new Point(2, 3);
		Point p32 = new Point(3, 2);
		points.add(p11);
		points.add(p1010);
		points.add(p110);
		points.add(p12);
		points.add(p23);
		points.add(p32);
		for(Point pt : convexHull(points)) {
			System.out.print(pt.x() + "\t");
			System.out.println(pt.y());
			//System.exit(0);
		}
    }
}
