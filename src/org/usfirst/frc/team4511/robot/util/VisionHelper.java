package org.usfirst.frc.team4511.robot.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;



import org.usfirst.frc.team4511.robot.Robot;
import org.usfirst.frc.team4511.robot.commands.Pair;

public class VisionHelper {
	private static double[] xPos, tempXPos, yPos, tempYPos, xWidth, tempXWidth, defaul = new double[0];
	private static List<Triple<Double, Integer, Integer>> compair = new ArrayList();
	private static final int cameraX = 640;
	public static void update(){
		tempXPos = Robot.table.getNumberArray("centerX", defaul);
		tempYPos = Robot.table.getNumberArray("centerY", defaul);
		tempXWidth = Robot.table.getNumberArray("width", defaul);
		if (tempYPos.length == 2){
			xPos = tempXPos;
			yPos = tempYPos;
		}else if (xPos.length > 2){
			compair.clear();
			for (int i = 0; i < yPos.length; i++){
				for (int j = 0; j < yPos.length; j++){
					if (!compair.stream().anyMatch((Triple<Double, Integer, Integer> arg0) -> {
						final boolean[] match = {false};
						compair.forEach((Triple<Double, Integer, Integer> t) -> {
							match[0] |= t.f.equals(arg0.f) || t.g.equals(arg0.g);
						});
						return match[0];
					})){
						compair.add(new Triple<Double, Integer, Integer>(Math.abs(yPos[i] - yPos[j]), i, j));
					}
				}
			}
			final AtomicReference<Triple<Double, Integer, Integer>> chosen = new AtomicReference<>(compair.get(0));
			compair.forEach((Triple<Double, Integer, Integer> t) -> {
				if (t.e < chosen.get().e){
					chosen.set(t);
				}
			});
			xPos = new double[2];
			yPos = new double[2];
			xPos[0] = tempXPos[chosen.get().f];
			yPos[0] = tempYPos[chosen.get().f];
			xWidth[0] = tempXWidth[chosen.get().f];
			xPos[1] = tempXPos[chosen.get().g];
			yPos[1] = tempYPos[chosen.get().g];
			xWidth[1] = tempXWidth[chosen.get().g];
		}else{
			System.out.println("Only " + getCount() + " contuers");
		}
	}
	public static int getCount(){
		return tempXPos.length;
	}
	public static double getApprovedXPos(){
		return -((xPos[1]-xPos[0])-(cameraX/2))/(cameraX/2);
	}
	public static double getApprovedDistance(){
		return (835*2/xWidth[0]);
	}
	
	private static class Triple<E, F, G>{
		E e;
		F f;
		G g;
		Triple(E e, F f, G g){
			this.e = e;
			this.f = f;
			this.g = g;
		}
	}
}
