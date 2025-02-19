import java.awt.Color;
import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {

	public static void main(String[] args) {
		double lv = 0.005;
		double uv = 0.01;
		int b = 3;
		double r = 0.025;
		int s = 0;
		int hs = 0;
		double px = 0.5;
		double py = 0.5;
		double ps = 0.01;
		double[] bx = new double[b];
		double[] bY = new double[b];
		double[] bxv = new double[b];
		double[] bYv = new double[b];
		
		for(int i = 0; i < b; i++) {
			bx[i] = Math.random();
			bY[i] = Math.random();
			bxv[i] = Math.random() * (uv - lv) + lv;
			bYv[i] = Math.random() * (uv - lv) + lv;
		}
		
		StdDraw.enableDoubleBuffering();
		
		long st = System.currentTimeMillis();
		long dt = System.currentTimeMillis();
		
		while (true) {
			
			StdDraw.clear();
			boolean c = false;
			for(int i = 0; i < b; i++) {
				
			
				bx[i] = bx[i] + bxv[i];
				bY[i] = bY[i] + bYv[i];
				if(bx[i] + r > 1 || bx[i] - r < 0) { 
					bxv[i] = -bxv[i];
				}
				if(bY[i] + r > 1 || bY[i] - r < 0) { 
					bYv[i] = -bYv[i];
				}
				for(int j = 0; j < b; j++) {
					if(i != j) {
						double d = Math.sqrt(Math.pow(bx[i] - bx[j], 2) + Math.pow(bY[i] - bY[j], 2));
						if(d < 2 * r) {
							bxv[i] = -bxv[i];
							bYv[i] = -bYv[i];
						}
					}
				}
				
				double d = Math.sqrt(Math.pow(bx[i] - px, 2) + Math.pow(bY[i] - py, 2));
				if(d < 2 * r) {
					c = true;
				}
			}
			
			if(c) {
				b = 3;
				for(int i = 0; i < b; i++) {
					bx[i] = Math.random();
					bY[i] = Math.random();
					bxv[i] = Math.random() * (uv - lv) + lv;
					bYv[i] = Math.random() * (uv - lv) + lv;
					s = 0;
					st = System.currentTimeMillis();
					dt = System.currentTimeMillis();
					px = 0.5;
					py = 0.5;
				}
				
				
			}
			
			if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
				py = py + ps;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_S)) {
				py = py - ps;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_A)) {
				px = px - ps;
			}
			if(StdDraw.isKeyPressed(KeyEvent.VK_D)) {
				px = px + ps;
			}
			
			if(px > 1) {
				px = 1;
			}
			if(px < 0) {
				px = 0;
			}
			if(py > 1) {
				py = 1;
			}
			if(py < 0) {
				py = 0;
			}
			
			long now = System.currentTimeMillis();
			if(now > st + 1000) {
				s++;
				if(s > hs) {
					hs = s;
				}
				st = now;
			}
			
			if(now > dt + 10000) {
				b++;
				double[] ballXnew = new double[b];
				double[] ballYnew = new double[b];
				double[] ballXVnew = new double[b];
				double[] ballYVnew = new double[b];
				for(int i = 0; i < b - 1; i++) {
					ballXnew[i] = bx[i];
					ballYnew[i] = bY[i];
					ballXVnew[i] = bxv[i];
					ballYVnew[i] = bYv[i];
				}
				ballXnew[b-1] = Math.random();
				ballYnew[b-1] = Math.random();
				ballXVnew[b-1] = Math.random() * (uv - lv) + lv;
				ballYVnew[b-1] = Math.random() * (uv - lv) + lv;
				bx = ballXnew;
				bY = ballYnew;
				bxv = ballXVnew;
				bYv = ballYVnew;
				dt = now;
			}
			StdDraw.setPenColor(Color.red);
			for(int i = 0; i < b; i++) {
				StdDraw.filledCircle(bx[i], bY[i], r);
			}
			
			StdDraw.setPenColor(Color.black);
			StdDraw.filledCircle(px, py, r);
			StdDraw.text(0.5, 0.1, "Score: " + s + " High Score: " + hs);
			
			StdDraw.show();
			StdDraw.pause(10);
			
		}
	}
}
