package checkFL;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main {
	public static String LINENUMBER = "lineNumber";
	public static String SUSP = "susp";
	public static String CLASSNAME = "className";
	public static String METHODNAME = "methodName";
	public static String HOME = "/home/apr/workspace/examScore/defects4j_checkout_math";
	
	public static void main(String[] args) {
		List<String> target_list = new ArrayList<String>();
		List<String> errorClass = new ArrayList<String>();
		List<errorLines> errorLine = new ArrayList<errorLines>();
		
		target_list.add("Math2");		
		target_list.add("Math5");
		target_list.add("Math7");		
		target_list.add("Math8");		
		target_list.add("Math28");		
		target_list.add("Math40");		
		target_list.add("Math49");		
		target_list.add("Math50");		
		target_list.add("Math53");				
		target_list.add("Math70");				
		target_list.add("Math73");
		target_list.add("Math78");
		target_list.add("Math80");		
		target_list.add("Math81");		
		target_list.add("Math82");		
		target_list.add("Math84");		
		target_list.add("Math85");
		
		errorClass.add("org.apache.commons.math3.distribution.HypergeometricDistribution"); //Math2
		errorClass.add("org.apache.commons.math3.complex.Complex"); //Math5
		errorClass.add("org.apache.commons.math3.ode.AbstractIntegrator"); //Math7
		errorClass.add("org.apache.commons.math3.distribution.DiscreteDistribution"); //Math8
		errorClass.add("org.apache.commons.math3.optimization.linear.SimplexSolver"); //Math28
		errorClass.add("org.apache.commons.math.analysis.solvers.BracketingNthOrderBrentSolver"); //Math40
		errorClass.add("org.apache.commons.math.util.OpenIntToDoubleHashMap"); //Math49
		errorClass.add("org.apache.commons.math.analysis.solvers.BaseSecantSolver"); //Mat50
		errorClass.add("org.apache.commons.math.complex.Complex"); //Mat53
		errorClass.add("org.apache.commons.math.analysis.solvers.BisectionSolver"); //Math70
		errorClass.add("org.apache.commons.math.analysis.solvers.BrentSolver"); //Math73
		errorClass.add("org.apache.commons.math.ode.events.EventState"); //Math78
		errorClass.add("org.apache.commons.math.linear.EigenDecompositionImpl"); //Math80
		errorClass.add("org.apache.commons.math.linear.EigenDecompositionImpl"); //Math81
		errorClass.add("org.apache.commons.math.optimization.linear.SimplexSolver"); //Math82
		errorClass.add("org.apache.commons.math.optimization.direct.MultiDirectional"); //Math84
		errorClass.add("org.apache.commons.math.analysis.solvers.UnivariateRealSolverUtils"); //Math85

		List<twoInt> tlist = new ArrayList<twoInt>();
		tlist.add(new twoInt(268, 268));
		errorLine.add(new errorLines(tlist)); //Math2
		tlist.clear();
		
		tlist.add(new twoInt(305, 305));
		errorLine.add(new errorLines(tlist)); //Math5
		tlist.clear();
		
		tlist.add(new twoInt(346, 349));
		tlist.add(new twoInt(357, 359));
		tlist.add(new twoInt(362, 365));
		tlist.add(new twoInt(370, 372));
		errorLine.add(new errorLines(tlist)); //Math7
		tlist.clear();
		
		tlist.add(new twoInt(181, 181));
		tlist.add(new twoInt(187, 187));
		errorLine.add(new errorLines(tlist)); //Math8
		tlist.clear();
		
		tlist.add(new twoInt(119, 119));
		tlist.add(new twoInt(140, 140));
		errorLine.add(new errorLines(tlist)); //Math28
		tlist.clear();
		
		tlist.add(new twoInt(235, 238));
		tlist.add(new twoInt(241, 244));
		errorLine.add(new errorLines(tlist)); //Math40
		tlist.clear();
		
		tlist.add(new twoInt(345, 345));
		tlist.add(new twoInt(358, 358));
		tlist.add(new twoInt(370, 370));
		tlist.add(new twoInt(383, 383));
		errorLine.add(new errorLines(tlist)); //Math49
		tlist.clear();
		
		tlist.add(new twoInt(187, 190));
		errorLine.add(new errorLines(tlist)); //Math50
		tlist.clear();
		
		tlist.add(new twoInt(153, 155));
		errorLine.add(new errorLines(tlist)); //Math53
		tlist.clear();
		
		tlist.add(new twoInt(72, 72));
		errorLine.add(new errorLines(tlist)); //Math70
		tlist.clear();
		
		tlist.add(new twoInt(136, 139));
		errorLine.add(new errorLines(tlist)); //Math73
		tlist.clear();
		
		tlist.add(new twoInt(191, 191));
		tlist.add(new twoInt(199, 205));
		tlist.add(new twoInt(207, 209));
		errorLine.add(new errorLines(tlist)); //Math78
		tlist.clear();
		
		tlist.add(new twoInt(1135, 1135));
		errorLine.add(new errorLines(tlist)); //Math80
		tlist.clear();
		
		tlist.add(new twoInt(603, 603));
		tlist.add(new twoInt(905, 907));
		tlist.add(new twoInt(1543, 1544));
		errorLine.add(new errorLines(tlist)); //Math81
		tlist.clear();
		
		tlist.add(new twoInt(82, 82));
		errorLine.add(new errorLines(tlist)); //Math82
		tlist.clear();
		
		tlist.add(new twoInt(64, 64));
		tlist.add(new twoInt(93, 94));
		tlist.add(new twoInt(97, 102));
		errorLine.add(new errorLines(tlist)); //Math84
		tlist.clear();
		
		tlist.add(new twoInt(198, 198));
		errorLine.add(new errorLines(tlist)); //Math85
		tlist.clear();
		
		
		for(int i = 0; i < target_list.size(); i++) {
			//if(target_list.get(i).equals("Math78")) {
				String s = HOME 
						+ "/Math_patch2_FL/"
						+ target_list.get(i)
						+ "_patch2_FL.txt";
				calculateExamScore(s, errorClass.get(i), errorLine.get(i), 85000);
			//}
		}
		
	}
	
	public static void calculateExamScore(String dir, String errorClass, errorLines errorLine, int entireLine) {
		try {
			File file_FL= new File(dir);
			if(!file_FL.exists()) {
				System.out.println(dir + " not exists!");
				return;
			}
			
			
			Scanner scan = new Scanner(file_FL);
			if(scan.hasNextLine()) {
				String text = scan.nextLine();
				scan.close();
				
				List<String> spText = new ArrayList<String>(Arrays.asList(text.split("Candidate ")));
				
				for(int i = 1; i < spText.size(); i++)
					spText.set(i-1, spText.get(i));
				spText.remove(spText.size()-1);
				
//				for(int i = 0; i < spText.size(); i++)
//					System.out.println(i + "th " + spText.get(i));
				
				
				List<FLresult> result = new ArrayList<FLresult>();
				for(int i = 0; i < spText.size(); i++) {
					int classNameIndex = spText.get(i).lastIndexOf(CLASSNAME);
					int methodNameIndex = spText.get(i).lastIndexOf(METHODNAME);
					int lineIndex = spText.get(i).lastIndexOf(LINENUMBER);
					int suspIndex = spText.get(i).lastIndexOf(SUSP);
					lineIndex += LINENUMBER.length();
					suspIndex += SUSP.length();
					classNameIndex += CLASSNAME.length();
					methodNameIndex += METHODNAME.length();
					
					if(lineIndex != -1 && suspIndex != -1
							&& classNameIndex != -1 && methodNameIndex != -1){
						FLresult node = new FLresult();
						node.rank = i+1;
						node.lineNumber = Integer.parseInt(findContent(spText.get(i), lineIndex));
						node.susp = Double.parseDouble(findContent(spText.get(i), suspIndex));
						node.className = findContent(spText.get(i), classNameIndex);
						node.methodName = findContent(spText.get(i), methodNameIndex);
						result.add(node);
					}else {
						System.out.println("wrong log_FL file");
					}
				}
				
				
//				for(int i = 0; i < result.size(); i++) {
//					System.out.println(result.get(i).rank + ", "
//				+ result.get(i).lineNumber + ", " 
//				+ result.get(i).susp + ", "
//				+ result.get(i).className + ", "
//				+ result.get(i).methodName);
//				}
				
				for(int i = 0; i < result.size(); i++) {
					if(result.get(i).className.indexOf("evosuite") != -1) {
						result.remove(i);
						for(int j = i; j < result.size(); j++)
							result.get(j).rank--;
						i--;
					}else if(result.get(i).className.indexOf("ESTest") != -1) {
						result.remove(i);
						for(int j = i; j < result.size(); j++)
							result.get(j).rank--;
						i--;
					}else if(result.get(i).className.indexOf("ch.qos.logback") != -1) {
						result.remove(i);
						for(int j = i; j < result.size(); j++)
							result.get(j).rank--;
						i--;
					}else if(result.get(i).className.indexOf("org.slf4j") != -1) {
						result.remove(i);
						for(int j = i; j < result.size(); j++)
							result.get(j).rank--;
						i--;
					}
				}
				
				
				
//				System.out.println("");
//				System.out.println("after delete evosuite class");
//				for(int i = 0; i < result.size(); i++) {
//					System.out.println(result.get(i).rank + ", "
//				+ result.get(i).lineNumber + ", " 
//				+ result.get(i).susp + ", "
//				+ result.get(i).className + ", "
//				+ result.get(i).methodName);
//				}
//				
				
				
				double examScore = -1.f;
				boolean isfind = false;
				for(int i = 0; i < result.size(); i++) {
					if(errorClass.contentEquals(result.get(i).className)) {
						for(int j = 0; j < errorLine.lines.size(); j++) {
							if(errorLine.lines.get(j).a <= result.get(i).lineNumber && 
									result.get(i).lineNumber <= errorLine.lines.get(j).b){
								//examScore = (double)result.get(i).rank / (double)entireLine;
								examScore = (double)result.get(i).rank;
								isfind = true;
								break;
							}
						}
						
					}
					if(isfind)
						break;
				}
				
				
				if(examScore == -1.f) {
					System.out.println(dir + " cannot find error line");
				}else {
					System.out.println(dir + " exam score : " + examScore);
				}
				
			}
			else {
				System.out.println("empty scanner");	
			}
		}catch(FileNotFoundException e) {
			
		}catch(IOException e) {
			System.out.println(e);
		}
	}
	
	public static String findContent(String s, int index) {
		String string = "";
		while(true) {
			char c = s.charAt(index);
			if(c == '=') {
				index++;
				continue;
			}else if(c != ',' && c != ']' && c != '{') {
				string += c;
				index++;
			}else {
				break;
			}
		}
		return string;
	}


}
