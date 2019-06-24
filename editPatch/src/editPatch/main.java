package editPatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
	public static String HOME = "/home/apr/workspace/examScore/defects4j_checkout_math";
	
	public static void main(String[] args) {
		List<String> target_list = new ArrayList<String>();
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
		
		for(int i = 0; i < target_list.size(); i++)
			adjustPatch(target_list.get(i), 3, false);
			
		
	}
	
	public static void adjustPatch(String target, int n, boolean undo) {
		List<String> patch_text = new ArrayList<String>();
		List<String> src_text = new ArrayList<String>();
		File file_edit_target;
		File file_patch;
		
		int startline = 0;
		int lines = 0;
		
		String targetIndexs = "";
		for(int i = 0; i < target.length(); i++) {
			if('0' <= target.charAt(i) && target.charAt(i) <= '9')
				targetIndexs += target.charAt(i);
		}
		String patch_dir = HOME
				+ "/patch/Math/"
				+ "patch"
				+ n
				+ "-Math-"
				+ targetIndexs
				+ "-JGenProg2017.patch";
		
		file_patch = new File(patch_dir);
		if(!file_patch.exists()) {
			System.out.println(patch_dir + " not exists!");
			return;
		}
		
		
		Scanner scan;
		try {
			scan = new Scanner(file_patch);			
			//get patch text
			while(scan.hasNextLine()) {
				patch_text.add(scan.nextLine());	
			}
			scan.close();
	
			
			String edit_src_dir = patch_text.get(0).substring(patch_text.get(0).indexOf("/"), patch_text.get(0).length());
			edit_src_dir = edit_src_dir.substring(0, edit_src_dir.indexOf("//")) + edit_src_dir.substring(edit_src_dir.indexOf("//") + 1);
			String edit_dir = HOME
					+ "/Math/"
					+ target
					+ edit_src_dir;
			
			file_edit_target = new File(edit_dir);
			if(!file_edit_target .exists()) {
				System.out.println(patch_dir + " not exists!");
				return;
			}
			scan = new Scanner(file_edit_target);
			
			//get target src text
			while(scan.hasNextLine()) {
				src_text.add(scan.nextLine());
			}
			scan.close();
			
			//get start line and lines to edit
			for(int i = 0; i < patch_text.size(); i++) {
				if(patch_text.get(i).charAt(0) == '@') {
					//get start line to edit
					int index = patch_text.get(i).indexOf("+") + 1;
					String startline_s = "";
					while(true) {
						boolean flag = false;
						switch(patch_text.get(i).charAt(index)) {
						case ',' :
							flag = true;
							break;
						default:
							startline_s += patch_text.get(i).charAt(index);
							break;
						}
						
						if(flag)
							break;
						
						index++;
					}
					startline = Integer.parseInt(startline_s);
					
					//get lines to edit
					index++;
					String lines_s = "";
					while(true) {
						boolean flag = false;
						switch(patch_text.get(i).charAt(index)) {
						case ' ':
							flag = true;
							break;
						default :
							lines_s += patch_text.get(i).charAt(index);
							break;
						}
						
						if(flag)
							break;
						
						index++;
					}
					lines = Integer.parseInt(lines_s);
					
				}
			}
			startline--; //src_text index start from 0 not 1
			
			if(!undo) {
				int d = 3;
				for(int i = 3; i < patch_text.size(); i++) {
					switch(patch_text.get(i).charAt(0)) {
					case '-':
						src_text.remove(i-d + startline);
						d++;
						break;
					case '+':
						src_text.add(i-d + startline, patch_text.get(i).substring(1));
						break;
					}
				}
				
			}else {
				int d = 3;
				for(int i = 3; i < patch_text.size(); i++) {
					switch(patch_text.get(i).charAt(0)) {
					case '+':
						src_text.remove(i-d + startline);
						d++;
						break;
					case '-':
						src_text.add(i-d + startline, patch_text.get(i).substring(1));
						break;
					}
				}
			}
			
			PrintWriter fw = new PrintWriter(file_edit_target);
			for(int i = 0; i < src_text.size(); i++)
				fw.println(src_text.get(i));
					
			fw.close();
			
			if(!undo) {
				System.out.println(patch_dir + " adjust success");
			}else {
				System.out.println(patch_dir + " undo success");
			}
			
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
