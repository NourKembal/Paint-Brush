/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import GeoShapes.Circle;
import GeoShapes.FreeLine;
import GeoShapes.Line;
import GeoShapes.Rectangle;
import java.applet.Applet;
import java.awt.BasicStroke;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

/**
 *
 * @author Nour
 */
public class Paint extends Applet {

   
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */
    Image offscreenImage;
    Graphics offscreenGraphics;
    Vector<FreeLine> freelines;
    Vector<Point> erasedPositions;
    Vector<Line> lines;
    Vector<Circle> circles;
    Vector<Rectangle> rectangles;
    Vector<String> drawSequence;
    FreeLine dummyFreeLine;
    Line dummyLine;
    Circle dummyCircle;
    Rectangle dummyRectangle;
    Checkbox fillShape;
    Checkbox dottShape;
    Button lineBtn;
    Button rectBtn;
    Button cirBtn;
    Button blackBtn;
    Button redBtn;
    Button greenBtn;
    Button blueBtn;
    Button eraserBtn;
    Button clearBtn;
    Button undoBtn;
    Button freeHandBtn;
    Color currentColor;
    char shape;
    boolean fillChk;
    boolean dottChk;
    boolean eraseChk;
    int currentLineIndex;
    int currentRectIndex;
    int currentCirIndex;
    int currentFreeIndex;
    
    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        
        this.resize(1000, 500);
        
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
            }

            @Override
            public void componentMoved(ComponentEvent e) {
            }

            @Override
            public void componentShown(ComponentEvent e) {
            }

            @Override
            public void componentHidden(ComponentEvent e) {
            }
        });
        dummyCircle=null;
        dummyFreeLine=null;
        dummyLine=null;
        dummyRectangle=null;
        currentLineIndex=0;
        currentRectIndex=0;
        currentCirIndex=0;
        currentFreeIndex=0;
        fillChk=false;
        dottChk=false;
        eraseChk=false;
        currentColor=Color.BLACK;
        shape='f';
        lines=new Vector<>();
        circles=new Vector<>();
        rectangles=new Vector<>();
        erasedPositions=new Vector<>();
        drawSequence=new Vector<>();
        freelines=new Vector<>();
        fillShape=new Checkbox("Filled", false);
        fillShape.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                    fillChk=true;
                if(e.getStateChange()==ItemEvent.DESELECTED)
                    fillChk=false;
            }
        });
        dottShape=new Checkbox("Dotted", false);
        dottShape.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()==ItemEvent.SELECTED)
                    dottChk=true;
                if(e.getStateChange()==ItemEvent.DESELECTED)
                    dottChk=false;
            }
        });
        lineBtn=new Button("Line");
        lineBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape='l';
                eraseChk=false;
                repaint();
            }
        });
        rectBtn=new Button("Rectangle");
        rectBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape='r';
                eraseChk=false;
                repaint();
            }
        });
        cirBtn=new Button("Circle");
        cirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape='c';
                eraseChk=false;
                repaint();
            }
        });
        blackBtn=new Button("Black");
        blackBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.BLACK;
                repaint();
            }
        });
        redBtn=new Button("Red");
        redBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.RED;
                repaint();
            }
        });
        greenBtn=new Button("Green");
        greenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.GREEN;
                repaint();
            }
        });
        blueBtn=new Button("Blue");
        blueBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentColor=Color.BLUE;
                repaint();
            }
        });
        eraserBtn=new Button("Eraser");
        eraserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eraseChk=true;
                repaint();
            }
        });
        clearBtn=new Button("Clear all");
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lines.clear();
                rectangles.clear();
                circles.clear();
                erasedPositions.clear();
                drawSequence.clear();
                freelines.clear();
                currentLineIndex=0;
                currentRectIndex=0;
                currentCirIndex=0;
                currentFreeIndex=0;
                repaint();
            }
        });
        undoBtn=new Button("Undo");
        undoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!drawSequence.isEmpty())
                {
                    String s=drawSequence.lastElement();
                    drawSequence.removeElementAt(drawSequence.size()-1);
                    switch (s)
                    {
                        case "line":
                            lines.removeElementAt(lines.size()-1);
                            currentLineIndex--;
                            break;
                        case "rectangle":
                            rectangles.removeElementAt(rectangles.size()-1);
                            currentRectIndex--;
                            break;
                        case "circle":
                            circles.removeElementAt(circles.size()-1);
                            currentCirIndex--;
                            break;
                        case "eraser":
                            erasedPositions.removeElementAt(erasedPositions.size()-1);
                            break;
                        case "freeLine":
                            freelines.removeElementAt(freelines.size()-1);
                            currentFreeIndex--;
                            break;
                        default:
                            break;
                    }
                    repaint();
                }
            }
        });
        freeHandBtn=new Button("Free Hand");
        freeHandBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape='f';
                eraseChk=false;
                repaint();
            }
        });
        this.add(freeHandBtn);
        this.add(lineBtn);
        this.add(rectBtn);
        this.add(cirBtn);
        this.add(blackBtn);
        this.add(redBtn);
        this.add(greenBtn);
        this.add(blueBtn);
        this.add(eraserBtn);
        this.add(undoBtn);
        this.add(clearBtn);
        this.add(fillShape);
        this.add(dottShape);
        this.addMouseMotionListener(new MyListener());
	this.addMouseListener(new MyListener());
    }
    
    @Override
    public void paint(Graphics g)
    {
        offscreenImage = createImage( getWidth(), getHeight());
        offscreenGraphics = offscreenImage.getGraphics();
        offscreenGraphics.clearRect(0, 0, getWidth(), getHeight());
        
        if(drawSequence.isEmpty())
            undoBtn.setEnabled(false);
        else
            undoBtn.setEnabled(true);
        showStatus();
        for(int i=0;i<lines.size();i++)
        {
            Line l=lines.get(i);
            if(l.getDotted())
            {
                Graphics2D g2d = (Graphics2D) offscreenGraphics.create();
                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                g2d.setStroke(dashed);
                g2d.setColor(l.getColor());
                g2d.drawLine(l.getStart().x, l.getStart().y, l.getEnd().x, l.getEnd().y);
                g2d.dispose();
            }
            else
            {
                offscreenGraphics.setColor(l.getColor());
                offscreenGraphics.drawLine(l.getStart().x, l.getStart().y, l.getEnd().x, l.getEnd().y);
            }
        }
        for(int i=0;i<rectangles.size();i++)
        {
            Rectangle r=rectangles.get(i);
            offscreenGraphics.setColor(r.getColor());
            if(r.getFilled())
            {
                offscreenGraphics.fillRect(r.getULP().x, r.getULP().y, r.getWidth(),r.getHeight());
            }
            else if(r.getDotted())
            {
                Graphics2D g2d = (Graphics2D) offscreenGraphics.create();
                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                g2d.setStroke(dashed);
                g2d.setColor(r.getColor());
                g2d.drawRect(r.getULP().x, r.getULP().y, r.getWidth(),r.getHeight());
                g2d.dispose();
            }
            else
            {
                offscreenGraphics.drawRect(r.getULP().x, r.getULP().y, r.getWidth(),r.getHeight());
            }
        }
        for(int i=0;i<circles.size();i++)
        {
            Circle c=circles.get(i);
            offscreenGraphics.setColor(c.getColor());
            if(c.getFilled())
            {
                offscreenGraphics.fillOval(c.getULP().x, c.getULP().y, c.getWidth(), c.getHeight());
            }
            else if(c.getDotted())
            {
                Graphics2D g2d = (Graphics2D) offscreenGraphics.create();
                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                g2d.setStroke(dashed);
                g2d.setColor(c.getColor());
                g2d.drawOval(c.getULP().x, c.getULP().y, c.getWidth(), c.getHeight());
                g2d.dispose();
            }
            else
            {
                offscreenGraphics.drawOval(c.getULP().x, c.getULP().y, c.getWidth(), c.getHeight());
            }
        }
        for(int i=0;i<freelines.size();i++)
        {
            FreeLine fl=freelines.get(i);
            offscreenGraphics.setColor(fl.getColor());
            Point stp=fl.pointsVec.firstElement();
            for(int j=1;j<fl.pointsVec.size();j++)
            {
                Point ep=fl.pointsVec.get(j);
                if(fl.getDotted())
                {
                    Graphics2D g2d = (Graphics2D) offscreenGraphics.create();
                    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2d.setStroke(dashed);
                    g2d.drawLine(stp.x, stp.y, ep.x, ep.y);
                    g2d.dispose();
                }
                else
                {
                    offscreenGraphics.drawLine(stp.x, stp.y, ep.x, ep.y);
                }
                stp=ep;
            }
        }
        for(int i=0;i<erasedPositions.size();i++)
        {
            offscreenGraphics.setColor(Color.WHITE);
            offscreenGraphics.fillRect(erasedPositions.get(i).x, erasedPositions.get(i).y, 10, 10);
        } 
        drawDummyElements();
         g.drawImage(offscreenImage, 0, 0, this);
    }
    
    @Override
     public void update(Graphics g) {
         paint(g);
     }
     
    @Override
     public void destroy() {
       offscreenGraphics.dispose();
     }
    
    private void showStatus()
    {
        offscreenGraphics.setColor(Color.GRAY);
        if(eraseChk)
        {
            offscreenGraphics.drawString("Using Eraser", 1, 15);
        }
        else
        {
            switch(shape)
            {
                case 'l':
                    offscreenGraphics.drawString("Using Line", 1, 15);
                    break;
                case 'r':
                    offscreenGraphics.drawString("Using Rectangle", 1, 15);
                    break;
                case 'c':
                    offscreenGraphics.drawString("Using Circle", 1, 15);
                    break;
                case 'f':
                    offscreenGraphics.drawString("Using Free Hand", 1, 15);
                    break;
                default:
                    break;
            }
           if(currentColor==Color.BLACK)
               offscreenGraphics.drawString("Color:Black", 1, 30);
           else if(currentColor==Color.BLUE)
               offscreenGraphics.drawString("Color:Blue", 1, 30);
           else if(currentColor==Color.RED)
               offscreenGraphics.drawString("Color:Red", 1, 30);
           else
               offscreenGraphics.drawString("Color:Green", 1, 30);
        }
    }

    private void drawDummyElements() {
        if(dummyLine!=null)
        {
            if(dummyLine.getDotted())
            {
                Graphics2D g2d = (Graphics2D) offscreenGraphics.create();
                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                g2d.setStroke(dashed);
                g2d.setColor(dummyLine.getColor());
                g2d.drawLine(dummyLine.getStart().x, dummyLine.getStart().y, dummyLine.getEnd().x, dummyLine.getEnd().y);
                g2d.dispose();
            }
            else
            {
                offscreenGraphics.setColor(dummyLine.getColor());
                offscreenGraphics.drawLine(dummyLine.getStart().x, dummyLine.getStart().y, dummyLine.getEnd().x, dummyLine.getEnd().y);
            }
        }
        if(dummyRectangle!=null)
        {
            offscreenGraphics.setColor(dummyRectangle.getColor());
            if(dummyRectangle.getFilled())
            {
                offscreenGraphics.fillRect(dummyRectangle.getULP().x, dummyRectangle.getULP().y, dummyRectangle.getWidth(),dummyRectangle.getHeight());
            }
            else if(dummyRectangle.getDotted())
            {
                Graphics2D g2d = (Graphics2D) offscreenGraphics.create();
                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                g2d.setStroke(dashed);
                g2d.setColor(dummyRectangle.getColor());
                g2d.drawRect(dummyRectangle.getULP().x, dummyRectangle.getULP().y, dummyRectangle.getWidth(),dummyRectangle.getHeight());
                g2d.dispose();
            }
            else
            {
                offscreenGraphics.drawRect(dummyRectangle.getULP().x, dummyRectangle.getULP().y, dummyRectangle.getWidth(),dummyRectangle.getHeight());
            }
        }
        if(dummyCircle!=null)
        {
            offscreenGraphics.setColor(dummyCircle.getColor());
            if(dummyCircle.getFilled())
            {
                offscreenGraphics.fillOval(dummyCircle.getULP().x, dummyCircle.getULP().y, dummyCircle.getWidth(), dummyCircle.getHeight());
            }
            else if(dummyCircle.getDotted())
            {
                Graphics2D g2d = (Graphics2D) offscreenGraphics.create();
                Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                g2d.setStroke(dashed);
                g2d.setColor(dummyCircle.getColor());
                g2d.drawOval(dummyCircle.getULP().x, dummyCircle.getULP().y, dummyCircle.getWidth(), dummyCircle.getHeight());
                g2d.dispose();
            }
            else
            {
                offscreenGraphics.drawOval(dummyCircle.getULP().x, dummyCircle.getULP().y, dummyCircle.getWidth(), dummyCircle.getHeight());
            }
        }
        if(dummyFreeLine!=null)
        {
            offscreenGraphics.setColor(dummyFreeLine.getColor());
            Point stp=dummyFreeLine.pointsVec.firstElement();
            for(int j=1;j<dummyFreeLine.pointsVec.size();j++)
            {
                Point ep=dummyFreeLine.pointsVec.get(j);
                if(dummyFreeLine.getDotted())
                {
                    Graphics2D g2d = (Graphics2D) offscreenGraphics.create();
                    Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
                    g2d.setStroke(dashed);
                    g2d.drawLine(stp.x, stp.y, ep.x, ep.y);
                    g2d.dispose();
                }
                else
                {
                    offscreenGraphics.drawLine(stp.x, stp.y, ep.x, ep.y);
                }
                stp=ep;
            }
        }
        
    }

    // TODO overwrite start(), stop() and destroy() methods
    class MyListener implements MouseMotionListener,MouseListener
	{
                public void mousePressed(MouseEvent e)
		{
                    if(eraseChk)
                    {
                        erasedPositions.add(e.getPoint());
                        drawSequence.addElement("eraser");
                    }
                    else
                    {
			switch(shape)
                        {
                            case 'l':
                                dummyLine=new Line(e.getPoint());
                                dummyLine.setColor(currentColor);
                                dummyLine.setFilled(false);
                                dummyLine.setDotted(dottChk);
                                //lines.add(currentLineIndex,dummyLine);
                                //drawSequence.addElement("line");
                                break;
                            case 'r':
                                dummyRectangle=new Rectangle();
                                dummyRectangle.setColor(currentColor);
                                dummyRectangle.setFilled(fillChk);
                                dummyRectangle.setDotted(dottChk);
                                dummyRectangle.setP1(e.getPoint());
                                //rectangles.add(currentRectIndex, dummyRectangle);
                                //drawSequence.addElement("rectangle");
                                break;
                            case 'c':
                                dummyCircle=new Circle();
                                dummyCircle.setColor(currentColor);
                                dummyCircle.setFilled(fillChk);
                                dummyCircle.setDotted(dottChk);
                                dummyCircle.setP1(e.getPoint());
                                //dummyCircle.setDiameter(0);
                                //circles.add(currentCirIndex,dummyCircle);
                                //drawSequence.addElement("circle");
                                break;
                            case 'f':
                                dummyFreeLine=new FreeLine();
                                dummyFreeLine.setColor(currentColor);
                                dummyFreeLine.setDotted(dottChk);
                                dummyFreeLine.addPoint(e.getPoint());
                                //freelines.add(currentFreeIndex, dummyFreeLine);
                                //drawSequence.addElement("freeLine");
                                break;
                            default:
                                break;
                        }
                    }
                    repaint();
		}
		public void mouseDragged(MouseEvent e)
		{
                     if(eraseChk)
                     {
                         erasedPositions.add(e.getPoint());
                         drawSequence.addElement("eraser");
                     }
                     else
                     {
			switch(shape)
                        {
                            case 'l':
                                dummyLine.setEnd(e.getPoint());
                                break;
                            case 'r':
                                dummyRectangle.setP2(e.getPoint());
                                dummyRectangle.calcWidth();
                                dummyRectangle.calcHeight();
                                break;
                            case 'c':
                                //Circle c=circles.get(currentCirIndex);
//                                double d=Math.hypot(dummyCircle.getCenter().x-e.getPoint().x, dummyCircle.getCenter().y-e.getPoint().y);
//                                if(e.getPoint().x<=dummyCircle.getCenter().x || e.getPoint().y <=dummyCircle.getCenter().y)
//                                {
//                                    dummyCircle.setCenter(e.getPoint());
//                                    dummyCircle.setDiameter(dummyCircle.getDiameter()+(int)d);
//                                }
//                                else
//                                {
//                                    dummyCircle.setDiameter((int)d);
//                                }
                                dummyCircle.setP2(e.getPoint());
                                dummyCircle.calcWidth();
                                dummyCircle.calcHeight();
                                break;
                            case 'f':
                                dummyFreeLine.addPoint(e.getPoint());
                                break;
                            default:
                                break;
                        }
                    }
                    repaint();
		}
		public void mouseReleased(MouseEvent e)
		{
                    if(!eraseChk)
                    {
                        switch(shape)
                        {
                            case 'l':
                                //currentLineIndex++;
                                if(!dummyLine.getStart().equals(dummyLine.getEnd()))
                                {
                                    lines.addElement(dummyLine);
                                    drawSequence.addElement("line");
                                }
                                dummyLine=null;
                                break;
                            case 'r':
                                //currentRectIndex++;
                                if(!dummyRectangle.getP1().equals(dummyRectangle.getP2()))
                                {
                                    rectangles.addElement(dummyRectangle);
                                    drawSequence.addElement("rectangle");
                                }
                                dummyRectangle=null;
                                break;
                            case 'c':
                                //currentCirIndex++;
                                if(!dummyCircle.getP1().equals(dummyCircle.getP2()))
                                {
                                    circles.addElement(dummyCircle);
                                    drawSequence.addElement("circle");
                                }
                                dummyCircle=null;
                                break;
                            case 'f':
                                //currentFreeIndex++;
                                if(dummyFreeLine.pointsVec.size()>1)
                                {
                                    freelines.addElement(dummyFreeLine);
                                    drawSequence.addElement("freeLine");
                                }
                                dummyFreeLine=null;
                                break;
                            default:
                                break;
                         }
                    }
		}
		public void mouseClicked(MouseEvent e)
		{}
		public void mouseEntered(MouseEvent e)
		{}
		public void mouseExited(MouseEvent e)
		{}
		public void mouseMoved(MouseEvent e)
		{}
	}
}
