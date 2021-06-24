package com;

public class ChainLink {

    private ChainLink left, right;
    
    public void append(ChainLink rightPart) {
        if (this.right != null)
            throw new IllegalStateException("Link is already connected.");

        this.right = rightPart;
        rightPart.left = this;
    }
    
    public Side longerSide() {   
    	
    	ChainLink currentChainLink = this;
    	int rightLength = 0;
    	
    	while (currentChainLink.right != null) {
    		rightLength += 1;
    		currentChainLink = currentChainLink.right;
    		if (this.equals(currentChainLink))
    			return Side.NONE;
    		}
    	currentChainLink = this;
    	int leftLength = 0;
    	
    	while (currentChainLink.left != null) {
    		leftLength += 1;
    		currentChainLink = currentChainLink.left;
    		
    		if (this.equals(currentChainLink))
    			return Side.NONE;
    		}
    	
    	if(rightLength>leftLength) {
    		return  Side.RIGHT;
    	}else {
    		return  Side.LEFT;
    	}
       // throw new UnsupportedOperationException("Waiting to be implemented.");
    }
    
    public static void main(String[] args) {
        ChainLink left = new ChainLink();
        ChainLink middle = new ChainLink();
        ChainLink right = new ChainLink();
        left.append(middle);
        middle.append(right);
        System.out.println(left.longerSide());
    }
}



  //left=middle  -->  middle   -- > right=middle
