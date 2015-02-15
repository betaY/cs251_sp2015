
public class SongsRecord {
	private int nSongs;
	
	private Song[] songsHeap;
	private int[] songsCount;
	private int[] songsPosition;

	public SongsRecord(int nSongs) {
		this.nSongs = nSongs;
		
		this.songsHeap = new Song[nSongs];
		this.songsCount = new int[nSongs];
		this.songsPosition = new int[nSongs];
	}
		
	public void addSongEntry(int songID, int numberOfTimesPlayed) {
		this.songsHeap[songID] = new Song(songID, numberOfTimesPlayed);
	}
	
	public Song[] getHeapOfSongs() {
		return this.songsHeap;
	}
	
	public int[] getPositionsOfSongs() {
		return this.songsPosition;
	}
	
	public int[] getSongPlayedCounts() {
		return this.songsCount;
	}
	
	public int getNumberOfSongs() {
		return this.nSongs;
	}

	public void process() {
		/* TODO Write code to:
			(1) Turn 'songsHeap' into a valid heap
			(2) Populate 'songsCount' and 'songsPosition' correctly */


		buildMaxHeap(songsHeap);
		for (int i = 0; i < songsHeap.length; i++) {
			// songsCount[i] = songsHeap[i].getNumberOfTimesPlayedSoFar();
            // songsPosition[i] = songsHeap[i].getSongID();
            songsPosition[songsHeap[i].getSongID()] = i;
            //songsCount[i] = songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar();
            // songsCount[songsHeap[i].getSongID()] = songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar();
		}
		for (int i = 0; i < songsHeap.length; i++) {
			songsCount[i] = songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar();
            // System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-= songID = %d, times = %d -=-=-=-=-=-=-=-=-=-=-=-=-=\n",
            //  songsHeap[i].getSongID(), songsHeap[i].getNumberOfTimesPlayedSoFar());
		}

		// System.out.println("");
		// for (int i = 0; i < songsHeap.length; i++) {
		// 	System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-= songsPosition["+i+"] = "+songsPosition[i]+" -=-=-=-=-=-=-=-=-=-=-=-=-=");
		// 	System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-= songsCount["+i+"] = "+songsCount[i]+" -=-=-=-=-=-=-=-=-=-=-=-=-=");
		// 	System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-= songsHeap[songPositions["+i+"]].getNumberOfTimesPlayedSoFar() = "+songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar()+" -=-=-=-=-=-=-=-=-=-=-=-=-=");
		// }


	}

	// build maxHeap
	public void buildMaxHeap(Song[] songsHeap) {
		int startIndex = getParentIndex(songsHeap.length - 1);
		for (int i = startIndex; i >= 0; i--) {
			maxHeapify(songsHeap, songsHeap.length, i);
		}
		for (int i = startIndex; i >= 0; i--) {
			checkId(songsHeap, songsHeap.length, i);
		}
	}
	public void checkId(Song[] songsHeap, int heapsize, int index) {
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);
		int largest = index;
		if (left < heapsize && songsHeap[largest].getNumberOfTimesPlayedSoFar() == songsHeap[left].getNumberOfTimesPlayedSoFar()) {
			if (songsHeap[largest].getSongID() > songsHeap[left].getSongID()) {
				largest = left;
			}
		}
		if (right < heapsize && songsHeap[largest].getNumberOfTimesPlayedSoFar() == songsHeap[right].getNumberOfTimesPlayedSoFar()) {
			if (songsHeap[largest].getSongID() > songsHeap[right].getSongID()) {
				largest = right;
			}
		}
		if (largest != index) {
			Song tmp = songsHeap[index];
			songsHeap[index] = songsHeap[largest];
			songsHeap[largest] = tmp;
			checkId(songsHeap, heapsize, largest);
		}
	}

	public static void maxHeapify(Song[] songsHeap, int heapsize, int index) {
		int left = getLeftChildIndex(index);
		int right = getRightChildIndex(index);

		int largest = index;
		// System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-= heapsize = %d -=-=-=-=-=-=-=-=-=-=-=-=-=\n",heapsize);
		// System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-= left = %d -=-=-=-=-=-=-=-=-=-=-=-=-=\n",left);
		// System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-= right = %d -=-=-=-=-=-=-=-=-=-=-=-=-=\n",right);
		// System.out.printf("-=-=-=-=-=-=-=-=-=-=-=-=-= largest = %d -=-=-=-=-=-=-=-=-=-=-=-=-=\n",largest);

		if (left < heapsize && songsHeap[largest].getNumberOfTimesPlayedSoFar() <= songsHeap[left].getNumberOfTimesPlayedSoFar()) {
			if (songsHeap[largest].getNumberOfTimesPlayedSoFar() == songsHeap[left].getNumberOfTimesPlayedSoFar()) {
				if (songsHeap[largest].getSongID() > songsHeap[left].getSongID())
					largest = left;
				else {
					largest = largest;
				}
			} else {
				largest = left;
			}
		}
		if (right < heapsize && songsHeap[largest].getNumberOfTimesPlayedSoFar() <= songsHeap[right].getNumberOfTimesPlayedSoFar()) {
			if (songsHeap[largest].getNumberOfTimesPlayedSoFar() == songsHeap[right].getNumberOfTimesPlayedSoFar()) {
				if (songsHeap[largest].getSongID() > songsHeap[right].getSongID())
					largest = right;
				else {
					largest = largest;
				}
			} else {
				largest = right;
			}
		}

		if (largest != index) {
			Song tmp = songsHeap[index];
			songsHeap[index] = songsHeap[largest];
			songsHeap[largest] = tmp;
			maxHeapify(songsHeap, heapsize, largest);
		}
	}

	public static int getParentIndex(int current) {
		return (current - 1) / 2;
	}
	public static int getLeftChildIndex(int current) {
		return current*2 + 1;
	}
	public static int getRightChildIndex(int current) {
		return current*2 + 2;
	}


	
	public void addSongOccurrence(int songID) {
		/* TODO Write code to:
			(1) Read 'songsPosition' and get the position of the song in the heap. Then increase the count by 1. After increasing the count by 1, move the song up if required and ensure that the heap invariant is maintained.
			(2) Modify 'songsPosition' and 'songsCount' accordingly to ensure that everything is consistent.
		*/
        songsHeap[songsPosition[songID]].increasePlayedCountByOne();
        // System.out.println("-----------------------"+PropertyTesters.testForHeapness(songsHeap)+"--------------------");
   //      if (PropertyTesters.testForHeapness(songsHeap) == false) {
   //          buildMaxHeap(songsHeap);
   //          for (int i = 0; i < songsHeap.length; i++) {
   //          	songsPosition[songsHeap[i].getSongID()] = i;
			// }
			// for (int i = 0; i < songsHeap.length; i++) {
			// 	songsCount[i] = songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar();
			// }

   //      } else {
        	
   //          for (int i = 0; i < songsHeap.length; i++) {
   //          	songsPosition[songsHeap[i].getSongID()] = i;
			// }
			// for (int i = 0; i < songsHeap.length; i++) {
			// 	songsCount[i] = songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar();
			// }
   //      }
        buildMaxHeap(songsHeap);
        for (int i = 0; i < songsHeap.length; i++) {
            	songsPosition[songsHeap[i].getSongID()] = i;
		}
		for (int i = 0; i < songsHeap.length; i++) {
			songsCount[i] = songsHeap[songsPosition[i]].getNumberOfTimesPlayedSoFar();
		}

	}

	public String getTopTwo() {
		String output = "";
		
		// Get top song
		output = Integer.toString(songsHeap[0].getSongID());
		output += " (" + Integer.toString(songsHeap[0].getNumberOfTimesPlayedSoFar())  + ")";
		
		// Get runner up
		int runnerUp = 2;
		if(songsHeap[1].getNumberOfTimesPlayedSoFar() > songsHeap[2].getNumberOfTimesPlayedSoFar() ||
				(songsHeap[1].getNumberOfTimesPlayedSoFar() == songsHeap[2].getNumberOfTimesPlayedSoFar() 
				&& songsHeap[1].getSongID() < songsHeap[2].getSongID()))
			runnerUp = 1;
		output += "; " + Integer.toString(songsHeap[runnerUp].getSongID());
		output += " (" + Integer.toString(songsHeap[runnerUp].getNumberOfTimesPlayedSoFar())  + ")";

		// output += "\n";
		// for (int i = 0; i < songsHeap.length; i++) {
		// 	output += Integer.toString(songsHeap[i].getSongID()) + " (" + Integer.toString(songsHeap[i].getNumberOfTimesPlayedSoFar())  + ")\n";
		// }



		return output;
	}
}
