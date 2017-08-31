package Test;

import java.util.Arrays;
import java.util.Random;

public class WeirdSort {
	
	private static int[] genArr(int len, Random r){
		int[] toReturn = new int[len];
		
		for(int i = 0; i < len; i++){
			toReturn[i] = r.nextInt(201) - 101;
		}
		return toReturn;
	}

	public static void main(String[] args){
		final int NUM_TEST = 100;
		Random r = new Random();
		
		for(int i = 0; i < NUM_TEST; i++){
			int[] toSort = genArr(r.nextInt(10000000), r);
			int[] res1;
			
			long start = System.currentTimeMillis();
			res1 = weirdSort(toSort);
			long end = System.currentTimeMillis();
			
			long myDiff = end - start;
			
			start = System.currentTimeMillis();
			Arrays.sort(toSort);
			end = System.currentTimeMillis();
			
			long theirDiff = end - start;
			
			assert(res1 == toSort);
			
			if(myDiff < theirDiff){
				System.out.println("Me: " + myDiff + " " + theirDiff);
			}
			else if(theirDiff < myDiff){
				System.out.println("Them: " + myDiff + " " + theirDiff);
			}
			else{
				System.out.println("Same: " + myDiff + " " + theirDiff);
			}
		}
	}
	
	private static int[] weirdSort(int[] toSort){
		if(toSort.length == 0){
			return new int[]{};
		}
		int[] toReturn = new int[toSort.length];
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int i: toSort){
			min = Math.min(min, i);
			max = Math.max(max, i);
		}
		
		int[] holder = new int[max - min + 1];
		for(int i: toSort){
			holder[i - min] ++;
		}
		
		int p1 = 0;
		for(int i = 0; i < holder.length; i++){
			while(holder[i] != 0){
				toReturn[p1] = i + min;
				p1 ++;
				holder[i] --;
			}
		}
		
		return toReturn;
	}
}
