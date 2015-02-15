public class PropertyTesters {
	
	public static boolean testForHeapness (Song[] songs) {
		/* TODO write code to test if the 'songs' array is a valid heap */
		// System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-= testForHeapness -=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        int startIndex = getParentIndex(songs.length-1);
        for (int i = startIndex; i >= 0; i--) {
            int check = checkHeapify(songs, songs.length, i);
            if (check == 0) {
                return false;
            }
        }
        return true;
	}
    
    public static int checkHeapify(Song[] songs, int heapSize, int index) {
        int left = getLeftChildIndex(index);
        int right = getRightChildIndex(index);
        int out = 0;
        
        int largest = index;
        // System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-= largest = %d -=-=-=-=-=-=-=-=-=-=-=-=-=\n",largest);
        // System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-= heapsize = %d -=-=-=-=-=-=-=-=-=-=-=-=-=\n",heapSize);

        
        // if (left < heapSize && (songs[largest].getNumberOfTimesPlayedSoFar() < songs[left].getNumberOfTimesPlayedSoFar()
        //  || songs[largest].getNumberOfTimesPlayedSoFar() < songs[right].getNumberOfTimesPlayedSoFar()))
        //     return 0;
        // else
        //     return 1;
        // if (left < heapSize && songs[largest].getNumberOfTimesPlayedSoFar() < songs[left].getNumberOfTimesPlayedSoFar()) {
        // 	return 0;
        // } else if (right < heapSize && (songs[largest].getNumberOfTimesPlayedSoFar() < songs[left].getNumberOfTimesPlayedSoFar() || songs[largest].getNumberOfTimesPlayedSoFar() < songs[right].getNumberOfTimesPlayedSoFar())) {
        // 	return 0;
        // } else {
        // 	return 1;
        // }
        if (left < heapSize && songs[largest].getNumberOfTimesPlayedSoFar() < songs[left].getNumberOfTimesPlayedSoFar()) {
			return 0;
		}
		if (right < heapSize && songs[largest].getNumberOfTimesPlayedSoFar() < songs[right].getNumberOfTimesPlayedSoFar()) {
			return 0;
		}
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
    

	//public static boolean check(Song[] songs, int position, int door){
	//	if(door == 0) { //maxheap
	//		if(2*position > songs.length())
	//			return true;
	//		else if (2*position == songs.length()) {
	//			if (songs[position-1].getSongID() > songs[2*position - 1].getSongID() && songs[position-1].getSongID() > songs[2*position].getSongID()) {
	//				return true;
	//			}else {
	//				return false;
	//			}
	//		}
	//		else if (songs[position-1].getSongID() > songs[2*position - 1].getSongID() && songs[position-1].getSongID() > songs[2*position].getSongID()) {
	//			return check(songs, position+1, door);
	//		} else {
	//			return false;
	//		}
	//	} else {
	//		if(2*position > songs.length())
	//			return true;
	//		else if (2*position == songs.length()) {
	//			if (songs[position-1].getSongID() < songs[2*position - 1].getSongID() && songs[position-1].getSongID() < songs[2*position].getSongID()) {
	//				return true;
	//			}else {
	//				return false;
	//			}
	//		}
	//		else if (songs[position-1].getSongID() < songs[2*position - 1].getSongID() && songs[position-1].getSongID() < songs[2*position].getSongID()) {
	//			return check(songs, position+1, door);
	//		} else {
	//			return false;
	//		}
	//	}
	//}



	
	public static boolean testDataConsistency (SongsRecord record) {
		int numberOfSongs = record.getNumberOfSongs();
		Song[] songsHeap = record.getHeapOfSongs();
		int[] songPositions = record.getPositionsOfSongs();
		int[] songsCounts = record.getSongPlayedCounts();
		
		boolean isConsistent = true;

		for(int i = 0; i < numberOfSongs && isConsistent; ++i) {
			isConsistent = isConsistent && (songsHeap[songPositions[i]].getNumberOfTimesPlayedSoFar() == songsCounts[i]); 
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-= songsCounts = "+isConsistent+" -=-=-=-=-=-=-=-=-=-=-=-=-=");
			isConsistent = isConsistent && (songPositions[songsHeap[i].getSongID()] == i);
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-= songPositions = "+isConsistent+" -=-=-=-=-=-=-=-=-=-=-=-=-=");
		}

		return isConsistent;
	}
}


