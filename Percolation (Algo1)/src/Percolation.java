import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
 private final  WeightedQuickUnionUF normalGrid;
 private final boolean[][] grid;
 private static int size;
 private int openSites;
 private int topSite;
 private int bottomSite;
 
	    // creates n-by-n grid, with all sites initially blocked
	    public Percolation(int n) {
	    	openSites = 0;
			if(n <=0 ) {
	    		throw new IllegalArgumentException("n cannont be 0 or less");
	    	}
	    	grid = new boolean[n][n];
	    	for(int i = 0; i < n;i++) {
	    		for(int j = 0;j<0;j++) {
	    			grid[i][j] = false;
	    		}
	    	}
	    		normalGrid = new WeightedQuickUnionUF(n*n+1);
	    		topSite = n*n;
	    		bottomSite= n*n+1;
	    		size = n;
	    		
	    	
	    }
	    public static int convertInto2d(int row, int col) {
	    	return row*size + col;
	    }

	    // opens the site (row, col) if it is not open already
	    public void open(int row, int col) {
	    	
	    	if(row <size && col<size) {
	    		if(!isOpen(row,col)){
	    		grid[row][col] = true;
	    		openSites += 1;
	    		if(this.isOpen(row-1,col)) {
		    		this.normalGrid.union(convertInto2d(row, col), convertInto2d(row-1, col));
		    		}
	    		if(this.isOpen(row+1,col)) {
		    		this.normalGrid.union(convertInto2d(row, col), convertInto2d(row+1, col));
		    		}
	    		if(this.isOpen(row,col-1)) {
		    		this.normalGrid.union(convertInto2d(row, col), convertInto2d(row, col-1));
		    		}
	    		if(this.isOpen(row,col+1)) {
		    		this.normalGrid.union(convertInto2d(row, col), convertInto2d(row, col+1));
		    		}
	    		if(row==0) {this.normalGrid.union(convertInto2d(row, col), topSite);}
	    		if(row==size-1) {this.normalGrid.union(convertInto2d(row, col), bottomSite);}
	    		
	    		}
	    }else throw new IllegalArgumentException("Row and Col must be within the bounds");
	    
	}    		
	    
	    	
	    
	    // is the site (row, col) open?
	    public boolean isOpen(int row, int col) {
	    	
	    		return grid[row][col];	
	   
	    }
	    

	    // is the site (row, col) full?
	    public boolean isFull(int row, int col) {
	    	if(this.normalGrid.connected(convertInto2d(row, col),topSite)) {
	    		return true;
	    	}else return false;
	    }

	    // returns the number of open sites
	    public int numberOfOpenSites() {
	    	return openSites;
	    }

	    // does the system percolate?
	    public  boolean percolates() {
	    	if(this.normalGrid.connected(topSite,bottomSite)) {
	    		return true;
	    	}else {return false;}
	    }

	    // test client (optional)
	    public static void main(String[] args) {
	  
	    	
	    }
	}

