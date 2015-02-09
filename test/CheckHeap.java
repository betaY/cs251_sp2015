import java.util.*;

public class CheckHeap {

	public static boolean check(int[] song) {
		int startIndex = getParentIndex(song.length-1);
		for (int i = startIndex; i >= 0; i--) {
			int check = checkHeapify(song, song.length, i);
			if (check == 0) {
				return false;
			}
		}
		return true;
	}
	public static int checkHeapify(int[] song, int heapSize, int index) {
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);

		int largest = index;

		if (left < heapSize && (song[index] < song[left] || song[index] < song[right]))
			return 0;
		else
			return 1;
	}


	public static int getParentIndex(int current) {
		return (current - 1)/2;
	}
	public static int getLeftChildIndex(int current) {
		return current*2 + 1;
	}
	public static int getRightChildIndex(int current) {
		return current*2 + 2;
	}

	public static void main(String[] args) {
		int[] test = {100, 19, 36, 17, 3, 25, 33, 2, 7};
		System.out.println(check(test));
	}
}