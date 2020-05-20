
package mminesweeper;

import javax.swing.*;
import java.util.*;
import java .awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class GUI extends JFrame{
    
    public boolean isaretleyici=false;
    public boolean resetter = false;
    Date startDate=new Date();
    Date endDate;
    int bosluk=5;
    public int mx=-100;
    public int my=-100;
    public boolean mutlu=true;
    int komsu=0;
    public int smileyX=605;
    public int smileyY=5;
    
    public int smileyCenterX=smileyX+35;
    public int smileyCenterY=smileyY+35;
    
    public int isaretX=445;
    public int isaretY=5;
    
    public int isaretCenterX=isaretX+35;
    public int isaretCenterY=isaretY+35;
    
     public int boslukX=90;
     public int boslukY=10;
     
    public int minusX=boslukX+160;
    public int minusY=boslukY;
    public int plusX=boslukX+240;
    public int plusY=boslukY;
    
    
    public int victoryMesX=700;
    public int victoryMesY=-50;
    String victoryMes="Henüz değil";
    public int timeX=1135;
    public int timeY=5;
    
    public int sec=0;
    
     public boolean victory=false;
     public boolean defeat=false;
    
    Random rand=new Random();
    
    int[][] mayinlar=new int[16][9];
    int[][] komsular=new int[16][9];
    boolean[][] revealed=new boolean[16][9];
    boolean[][] isaretlenmisler=new boolean[16][9];
    public GUI() {
        this.setTitle("Mayın Tarlası");
        this.setSize(1286,829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        
        for(int i=0;i<16;i++)
            {
                for(int j=0;j<9;j++)
                {
                if(rand.nextInt(100)<20)
                {
                    mayinlar[i][j]=1;
                }
                else {
                     mayinlar[i][j]=0;
                }
                 revealed[i][j]=false;
                }
               
                
            }
        for(int i=0;i<16;i++)
            {
                for(int j=0;j<9;j++)
                {
                    komsu=0;
                    for(int m=0;m<16;m++)
            {
                for(int n=0;n<9;n++)
                {
                    if(!(m == i && n == j))
                        
                    {
                        if(isN(i,j,m,n) == true)
                        {
                           komsu++; 
                        }
                    }
                }
                }
                    komsular[i][j]=komsu;
                }
            }
        
        Board board=new Board();
        this.setContentPane(board);
        Move move =new Move();
        this.addMouseMotionListener(move);
        Click click=new Click();
        this.addMouseListener(click);
    }
    
    public class Board extends JPanel{
        
        @Override
        public void paintComponent(Graphics g){
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, 1280,800);
            for(int i=0;i<16;i++)
            {
                for(int j=0;j<9;j++)
                {
                    g.setColor(Color.GRAY);
                    
                   
                    
                    if(revealed[i][j]==true)
                    {
                        g.setColor(Color.white);
                        if(mayinlar[i][j]==1)
                        {
                              g.setColor(Color.red);
                        }
                      
                    }
                    
                    if(mx>=bosluk+i*80 && mx<bosluk+i*80+80-2*bosluk && my>=bosluk+j*80+80+26 && my<bosluk+j*80+80+80+29-(2*bosluk))
                    {
                        g.setColor(Color.lightGray);
                    }
                    g.fillRect(bosluk+i*80, bosluk+j*80+80, 80-2*bosluk,80-2*bosluk);
                    if(revealed[i][j]==true)
                    {
                        g.setColor(Color.black);
                        if(mayinlar[i][j]==0 && komsular[i][j] != 0)
                        {
                            
                            if(komsular[i][j] == 1)
                            {
                                g.setColor(Color.BLUE);
                            }
                            else if(komsular[i][j] ==2)
                            {
                                g.setColor(Color.green);
                            }
                             else if(komsular[i][j] ==3)
                            {
                                g.setColor(Color.red);
                            }
                            else if(komsular[i][j] ==4)
                            {
                                g.setColor(new Color(0,0,128));
                            }
                            else if(komsular[i][j] ==5)
                            {
                                g.setColor(new Color(178,34,34));
                            }
                             else if(komsular[i][j]==6)
                            {
                                g.setColor(new Color(72,209,204));
                            }
                            else if(komsular[i][j]==8)
                            {
                                g.setColor(Color.darkGray);
                            }
                            
                            
                              g.setFont(new Font("Tahoma",Font.BOLD,40));
                              g.drawString(Integer.toString(komsular[i][j]), i*80+27, j*80+80+55);
                        }
                        else if(mayinlar[i][j] == 1)
                        {
                            g.fillRect(i*80+10+20,j*80+80+20,20,40);
                            g.fillRect(i*80+20,j*80+80+10+20,40,20);
                            g.fillRect(i*80+5+20,j*80+80+5+20,30,30);
                            g.fillRect(i*80+38,j*80+80+15,4,50);
                            g.fillRect(i*80+15,j*80+80+38,50,4); 
                            
                        }
                      
                    }
                    //isreleyici çizimi
                    if(isaretlenmisler[i][j] == true)
                    {
                g.setColor(Color.BLACK);
                g.fillRect(i*80+32, j*80+80+15, 5, 40);
                g.fillRect(i*80+20, j*80+80+50, 30, 10);
                g.setColor(Color.red);
                g.fillRect(i*80+16, j*80+80+15, 20, 15);
                g.setColor(Color.BLACK);
                g.drawRect(i*80+16, j*80+80+15, 20, 15);
                g.drawRect(i*80+17, j*80+80+16, 18, 13);
                g.drawRect(i*80+18, j*80+80+17, 16, 11);
                    }
                }
            }
            //Artı - Eksi
            g.setColor(Color.BLACK);
            g.fillRect(boslukX, minusY,300,60);
            g.setColor(Color.white);
            g.fillRect(minusX+5, minusY+10,40,40);
            g.fillRect(plusX+5, plusY+10,40,40);
            
            g.setFont(new Font("Tahoma",Font.PLAIN,35));
            g.drawString("BOŞLUK",boslukX+20,boslukY+45);
            
             g.setColor(Color.BLACK);
             g.fillRect(minusX+15, minusY+27,20,6);
             g.fillRect(plusX+15, plusY+27,20,6);
             g.fillRect(plusX+22, plusY+20,6,20);
             
             g.setColor(Color.white);
             if(bosluk < 10)
             {
                 g.setFont(new Font("Tahoma",Font.PLAIN,20));
                 g.drawString("0"+Integer.toString(bosluk),minusX+50,minusY+40);
                 
             }
             else{
                  g.setFont(new Font("Tahoma",Font.PLAIN,20));
                   g.drawString("0"+Integer.toString(bosluk),minusX+50,minusY+40);
             }
             
            
            // Gülen-Üzgün Surat Yapımı(Oyun kazanıldığında Gülen Surat-Kaybedildiğinde Üzgün Surat Gözükecek
                g.setColor(Color.yellow);
                g.fillOval(smileyX, smileyY,70,70);
                g.setColor(Color.BLACK);
                g.fillOval(smileyX+15, smileyY+20,10,10);
                g.fillOval(smileyX+45, smileyY+20,10,10); 
                if(mutlu==true)
                {
                    g.fillRect(smileyX+20, smileyY+50,30,5);
                    g.fillRect(smileyX+17, smileyY+45,5,5);
                    g.fillRect(smileyX+48, smileyY+45,5,5);
                }
                else {
                    g.fillRect(smileyX+20, smileyY+45,30,5);
                    g.fillRect(smileyX+17, smileyY+50,5,5);
                    g.fillRect(smileyX+48, smileyY+50,5,5);
                }
                
                // İşaretleyici Çizimi
                
                g.setColor(Color.BLACK);
                g.fillRect(isaretX+32+5, isaretY+15+5, 5, 40);
                g.fillRect(isaretX+20+5, isaretY+50+5, 30, 10);
                g.setColor(Color.red);
                g.fillRect(isaretX+16+5, isaretY+15+5, 20, 15);
                g.setColor(Color.BLACK);
                g.drawRect(isaretX+16+5, isaretY+15+5, 20, 15);
                g.drawRect(isaretX+17+5, isaretY+16+5, 18, 13);
                g.drawRect(isaretX+18+5, isaretY+17+5, 16, 11);
                if(isaretleyici == true)
                {
                    g.setColor(Color.red);
                }
                g.drawOval(isaretX, isaretY,70, 70);
                g.drawOval(isaretX+1, isaretY+1,68, 68);
                g.drawOval(isaretX+2, isaretY+2,66, 66);
                
                
                // Zaman Sayacı
                
                g.setColor(Color.black);
                g.fillRect(timeX,timeY, 140, 70);
                if(defeat == false && victory == false){
                    sec=(int)((new Date().getTime()-startDate.getTime())/1000);
                }
                
                if(sec > 999)
                {
                    sec=999;
                }
                g.setColor(Color.WHITE);
                if(victory == true)
                {
                    g.setColor(Color.green);
                }
                else if(defeat == true)
                {
                    g.setColor(Color.red);
                    
                }
                g.setFont(new Font("Tahoma",Font.PLAIN,80));
                if(sec < 10)
                {
                    g.drawString("00"+Integer.toString(sec),timeX,timeY+65);
                }
                else if(sec < 100)
                {
                    g.drawString("0"+Integer.toString(sec),timeX,timeY+65);
                }
                else{
                g.drawString(Integer.toString(sec),timeX,timeY+65);
                }
                
                if(victory == true)
                {
                    g.setColor(Color.GREEN);
                    victoryMes="KAZANDINIZ";
                }
                else if(defeat == true)
                {
                    g.setColor(Color.red);
                     victoryMes="KAYBETTİNİZ";
                }
                if(victory == true || defeat== true)
                {
                    victoryMesY= -50+ (int)(new Date().getTime() - endDate.getTime()) / 10;
                    if(victoryMesY > 65)
                    {
                        victoryMesY =65;
                    }
                    g.setFont(new Font("Tahoma",Font.PLAIN,50));
                    g.drawString(victoryMes,victoryMesX,victoryMesY);
                }
                
                
        }
    }
    public class Move implements MouseMotionListener{

        @Override
        public void mouseDragged(MouseEvent e) {
           
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx=e.getX();
            my=  e.getY();
            /*
            System.out.println("Mouse hareket ettirildi!");
            System.out.println("X="+mx+" "+"Y="+my);
            */
        }
        
        
    }
    public class Click implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            mx=e.getX();
            my=  e.getY();
            
            if(mx >= minusX+20 && mx < minusX+60 && my >= minusY+20 && my < minusY+60){
                bosluk--;
                if(bosluk < 1){
                    bosluk=1;
                    
                }
            }
            else if(mx >= plusX+20 && mx < plusX+60 && my >= plusY+20 && my < plusY+60)
            {
                bosluk++;
                 if(bosluk > 15){
                    bosluk=15;
                    
                }
            }
        if(inBoxX() != -1 && inBoxY() != -1)
        {
            
            System.out.println("Seçtiğiniz  Kutunun Konumu= "+"["+inBoxX()+ ","+inBoxY()+"]"+" Etrafında bulunan Mayın Sayısı="+komsular[inBoxX()][inBoxY()] );
            if(isaretleyici == true && revealed[inBoxX()][inBoxY()] == false)
            {
                if(isaretlenmisler[inBoxX()][inBoxY()] == false)
                {
                    isaretlenmisler[inBoxX()][inBoxY()] = true;
                }
                else{
                    isaretlenmisler[inBoxX()][inBoxY()] = false;
                }
            }
            else 
            {
                if(isaretlenmisler[inBoxX()][inBoxY()] == false){
                    revealed[inBoxX()][inBoxY()] =true;
                }
              
            }
        }
        
        else System.out.println("Herhangi Bir Kutu seçiniz");
        
        if(inSmiley()==true)
        {
            resetAll();
            
        }
          if(inIsaret() == true)
        {
            if(isaretleyici == false)
            {
                isaretleyici=true;
                System.out.println("isaretleyici=true");
            }
            else {
                 isaretleyici=false;
                System.out.println("isaretleyici=true");
            }
            
            
        }
        
        }

        @Override
        public void mousePressed(MouseEvent e) {
            
        }

        @Override
        public void mouseReleased(MouseEvent e) {
          
        }

        @Override
        public void mouseEntered(MouseEvent e) {
           
        }

        @Override
        public void mouseExited(MouseEvent e) {
            
        }
        
    }
    public void checkVictoryStatus(){
        if(defeat == false)
        {
         for(int i=0;i<16;i++) {
                for(int j=0;j<9;j++){
                    if(revealed[i][j] == true && mayinlar[i][j] == 1)
                    {
                        defeat=true;
                        mutlu=false;
                        endDate=new Date();
                    }
                    
                }
              }
        }
         if( totalBoxesRevealed() >= 144 -totalMines() && victory == false )
         {
             victory=true;
             endDate=new Date();
         }
        
    }
    
    public int totalMines(){
        int toplam=0;
        for(int i=0;i<16;i++) {
                for(int j=0;j<9;j++){
                    if(mayinlar[i][j] == 1)
                    {
                        toplam++;
                    }
                    
                }
              }
        return toplam;
    }
    
     public int totalBoxesRevealed(){
         int toplam=0;
        for(int i=0;i<16;i++) {
                for(int j=0;j<9;j++){
                    if(revealed[i][j] == true)
                    {
                        toplam++;
                    }
                    
                }
              }
              return 0;
     }
     
     
     
    public void resetAll()
            
    {
        
        isaretleyici=false;
        resetter=true;
        startDate=new Date();
        victoryMesY=-50;
        victoryMes="Henüz Değil";
        mutlu=true;
        victory=false;
        defeat=false;
        for(int i=0;i<16;i++)
            {
                for(int j=0;j<9;j++)
                {
                if(rand.nextInt(100)<20)
                {
                    mayinlar[i][j]=1;
                }
                else {
                     mayinlar[i][j]=0;
                }
                 revealed[i][j]=false;
                 isaretlenmisler[i][j]=false;
                }
               
                
            }
        for(int i=0;i<16;i++) {
            for(int j=0;j<9;j++) {
                    komsu=0;
                for(int m=0;m<16;m++) {
                     for(int n=0;n<9;n++) {
                      if(!(m == i && n == j)) {
                        if(isN(i,j,m,n) == true) {
                           komsu++; 
                        }
                    }
                }
                }
                    komsular[i][j]=komsu;
                }
            }
        resetter= false;
    }
    public boolean inSmiley(){
        int dif=(int)Math.sqrt(Math.abs(mx-smileyCenterX)*Math.abs(mx-smileyCenterX)+Math.abs(my-smileyCenterY)*Math.abs(my-smileyCenterY));
        if(dif <= 35)
        {
            return true;   
        }
         return false;   
    }
    public boolean inIsaret(){
        int dif=(int)Math.sqrt(Math.abs(mx-isaretCenterX)*Math.abs(mx-isaretCenterX)+Math.abs(my-isaretCenterY)*Math.abs(my-isaretCenterY));
        if(dif <= 35)
        {
            return true;   
        }
         return false;   
    }
    public int inBoxX()
    {
        for(int i=0;i<16;i++)
            {
                for(int j=0;j<9;j++)
                {
                    if(mx>=bosluk+i*80 && mx<bosluk+i*80+80-2*bosluk && my>=bosluk+j*80+80+26 && my<bosluk+j*80+80+80+29-(2*bosluk))
                    {
                       return i;
                    }
                   
                }
            }
        return -1;
    }
    public int inBoxY()
    {
        for(int i=0;i<16;i++)
            {
                for(int j=0;j<9;j++)
                {
                    if(mx>=bosluk+i*80 && mx<bosluk+i*80+80-2*bosluk && my>=bosluk+j*80+80+26 && my<bosluk+j*80+80+80+29-(2*bosluk))
                    {
                       return j;
                    }
                   
                }
            }
        return -1;
    }
    public boolean  isN(int mX,int mY,int cX,int cY)
    {
        if(mX-cX <2 && mX-cX >-2 && mY-cY <2 && mY-cY >-2 && mayinlar[cX][cY]==1 )
        {
            return true;
        }
        return false;
    }
}
