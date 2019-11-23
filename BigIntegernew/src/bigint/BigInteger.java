package bigint;

/**
 * This class encapsulates a BigInteger, i.e. a positive or negative integer with 
 * any number of digits, which overcomes the computer storage length limitation of 
 * an integer.
 * 
 */
public class BigInteger {

	/**
	 * True if this is a negative integer
	 */
	boolean negative;
	
	/**
	 * Number of digits in this integer
	 */
	int numDigits;
	
	/**
	 * Reference to the first node of this integer's linked list representation
	 * NOTE: The linked list stores the Least Significant Digit in the FIRST node.
	 * For instance, the integer 235 would be stored as:
	 *    5 --> 3  --> 2
	 *    
	 * Insignificant digits are not stored. So the integer 00235 will be stored as:
	 *    5 --> 3 --> 2  (No zeros after the last 2)        
	 */
	DigitNode front;
	
	/**
	 * Initializes this integer to a positive number with zero digits, in other
	 * words this is the 0 (zero) valued integer.
	 */
	public BigInteger() {
		negative = false;
		numDigits = 0;
		front = null;
	}
	
	/**
	 * Parses an input integer string into a corresponding BigInteger instance.
	 * A correctly formatted integer would have an optional sign as the first 
	 * character (no sign means positive), and at least one digit character
	 * (including zero). 
	 * Examples of correct format, with corresponding values
	 *      Format     Value
	 *       +0            0
	 *       -0            0
	 *       +123        123
	 *       1023       1023
	 *       0012         12  
	 *       0             0
	 *       -123       -123
	 *       -001         -1
	 *       +000          0
	 *       
	 * Leading and trailing spaces are ignored. So "  +123  " will still parse 
	 * correctly, as +123, after ignoring leading and trailing spaces in the input
	 * string.
	 * 
	 * Spaces between digits are not ignored. So "12  345" will not parse as
	 * an integer - the input is incorrectly formatted.
	 * 
	 * An integer with value 0 will correspond to a null (empty) list - see the BigInteger
	 * constructor
	 * 
	 * @param integer Integer string that is to be parsed
	 * @return BigInteger instance that stores the input integer.
	 * @throws IllegalArgumentException If input is incorrectly formatted
	 */
	public static BigInteger parse(String integer) 
	throws IllegalArgumentException {
		BigInteger pie = new BigInteger();
		int indexFirstDigit = 0;
		char digitChar;
		if(integer==null||integer.length()==0) {
			return null;
		}
		
		while(indexFirstDigit<=integer.length()-1) {
			digitChar = integer.charAt(indexFirstDigit);
			if (digitChar=='-') {
				indexFirstDigit+=1;
				pie.negative=true;
			}
			if(digitChar=='+') {
				indexFirstDigit+=1;
				pie.negative=false;
			}
			if(digitChar=='0') {
				indexFirstDigit++;
			}
			
			else if(digitChar!='0') {
				break;
				
			}
		}
		int start = integer.length()-1;

		char digitChar2 = integer.charAt(start);

		int holder = Integer.parseInt(String.valueOf(digitChar2));

		DigitNode N = new DigitNode(holder,null);

		while(start>=indexFirstDigit) {
			
			if(pie.front==null) {
				pie.front=N;
				start--;		
			}
			else {
			digitChar2 = integer.charAt(start);	
			holder = Integer.parseInt(String.valueOf(digitChar2));
			N.next= new DigitNode(holder,null);
			N=N.next;
			start--;
			pie.numDigits++;

			}
		}
		pie.numDigits++;

		
		
		/* IMPLEMENT THIS METHOD */
		
		// following line is a placeholder for compilation
		return pie;
	}
	
	/**
	 * Adds the first and second big integers, and returns the result in a NEW BigInteger object. 
	 * DOES NOT MODIFY the input big integers.
	 * 
	 * NOTE that either or both of the input big integers could be negative.
	 * (Which means this method can effectively subtract as well.)
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return Result big integer
	 */
	public static BigInteger add(BigInteger first, BigInteger second) {
		if(first==null&&second==null) {
			return null;
		}
		BigInteger pie2 = new BigInteger();
		// Returns either list if first or second is empty
		if(first==null) {
			pie2.front=second.front;
			return pie2;
			
		}
		if(second==null) {
			pie2.front=first.front;
			return pie2;
		}
	
		DigitNode sumValues = new DigitNode(0,null);
		DigitNode T = sumValues;		
		DigitNode firstNode=first.front;
		DigitNode secondNode = second.front;
		int sumNode=0;
		int carry = 0;
		int len1 = first.numDigits;
		int len2 = second.numDigits;
		if((first.negative==true||second.negative==true)&(first.negative!=second.negative)) {
			
			if(len1>len2) {
				
					while(secondNode!=null) {
						if(secondNode.digit>firstNode.digit) {
							sumNode=(firstNode.digit+10)-secondNode.digit;
							if(firstNode.next!=null) {
							firstNode.next.digit-=1;
							}
							T.next= new DigitNode(sumNode,null);
							T=T.next;
							firstNode=firstNode.next;
							secondNode=secondNode.next;
						
						}
						else {
							sumNode=(firstNode.digit-secondNode.digit);
							T.next=new DigitNode(sumNode,null);

							T=T.next;
							secondNode=secondNode.next;
							firstNode=firstNode.next;
						}

						
					}
					
					T.next=firstNode;
						
				
				
				if(first.negative=true&second.negative==false) {
					pie2.negative=true;
				}
				System.out.println(pie2.numDigits);
				pie2.front=sumValues.next;
				return pie2;
			}
			else if(len2>len1) {
				
					while(firstNode!=null) {
						if(firstNode.digit>secondNode.digit) {
							sumNode=(secondNode.digit+10)-firstNode.digit;
							if(secondNode.next!=null) {
							secondNode.next.digit-=1;
							}
							T.next= new DigitNode(sumNode,null);
							T=T.next;
							secondNode=secondNode.next;
							firstNode=firstNode.next;
						
						}
						else {
							sumNode=(secondNode.digit-firstNode.digit);
							T.next=new DigitNode(sumNode,null);
							T=T.next;
							firstNode=firstNode.next;
							secondNode=secondNode.next;
						}

						
					}
					T.next=secondNode;
						
				if(first.negative==false&second.negative==true) {
					pie2.negative=true;
				}
				

				pie2.front=sumValues.next;
				return pie2;
				
			}
			
			
				
			if(len1==len2) {
				if(first.negative==true&&second.negative==false) {
					while(secondNode!=null) {
						if(secondNode.digit>firstNode.digit) {
							sumNode=(firstNode.digit+10)-secondNode.digit;
							if(firstNode.next!=null) {
							firstNode.next.digit-=1;
							}
							T.next= new DigitNode(sumNode,null);
							T=T.next;
							firstNode=firstNode.next;
							secondNode=secondNode.next;
						
						}
						else {
							sumNode=(firstNode.digit-secondNode.digit);
							T.next=new DigitNode(sumNode,null);
							T=T.next;
							secondNode=secondNode.next;
							firstNode=firstNode.next;
						}

						
					}
					
					
					T.next=firstNode;
					pie2.front=sumValues.next;
					pie2.negative=true;
					return pie2;
					
				
				}
				else if(first.negative==false&&second.negative==true) {
					while(firstNode!=null) {
						if(firstNode.digit>secondNode.digit) {
							sumNode=(secondNode.digit+10)-firstNode.digit;
							if(secondNode.next!=null) {
							secondNode.next.digit-=1;
							}
							T.next= new DigitNode(sumNode,null);
							T=T.next;
							secondNode=secondNode.next;
							firstNode=firstNode.next;
						
						}
						else {
							sumNode=(secondNode.digit-firstNode.digit);
							T.next=new DigitNode(sumNode,null);
							T=T.next;
							firstNode=firstNode.next;
							secondNode=secondNode.next;
						}
						T.next=secondNode;
						pie2.front=sumValues.next;
						pie2.negative=true;
						return pie2;

						
					}
					
				}
			}
		
			
		
		}
		while(firstNode!=null||secondNode!=null) {
			if(firstNode!=null&&secondNode==null) {
				T.next=firstNode;
				break;
			}
			if(secondNode!=null&&firstNode==null) {
				T.next=secondNode;
				break;
			}
			
			if(firstNode!=null) {
				
				
					sumNode+=firstNode.digit;
				
			}
			if(secondNode!=null) {
				sumNode+=secondNode.digit;
			}
			if(sumNode>=10) {
				carry = 1;
				sumNode-=10;
				// Could do subtract Here...
				if(firstNode.next!=null) {
					firstNode.next.digit+=carry;
					
				}
				else if(firstNode.next==null&&secondNode.next!=null){
					secondNode.next.digit+=carry;
					
				}
				else if(firstNode.next==null&&secondNode.next==null) {
					firstNode.next=new DigitNode(carry,null);
					firstNode=firstNode.next;
				}
			}
			else {
				carry=0;
			}
			T.next = new DigitNode(sumNode, null);
			
			firstNode=firstNode.next;
			
			
			secondNode=secondNode.next;
			
			T=T.next;
			sumNode=0;
			if(carry!=0) {
				T.next= new DigitNode(carry,null);
			}
		}		
		
		if(first.negative==true&second.negative==true) {
			
				pie2.front=sumValues.next;
				pie2.negative=true;
				return pie2;
			
		}
		pie2.front=sumValues.next;
		return pie2;
	}
	
	/**
	 * Returns the BigInteger obtained by multiplying the first big integer
	 * with the second big integer
	 * 
	 * This method DOES NOT MODIFY either of the input big integers
	 * 
	 * @param first First big integer
	 * @param second Second big integer
	 * @return A new BigInteger which is the product of the first and second big integers
	 */
	//repeated addition
	public static BigInteger multiply(BigInteger first, BigInteger second) {
		BigInteger pie3 = new BigInteger();
		// returns Null if either parameter is Null
		if(first==null) {
			return null;
		}
		if (second == null) {
			return null;
		}
		DigitNode firstNode = first.front;
		DigitNode secondNode =second.front;
		int carry =0;
		int numZeros = 0;
		while(firstNode!=null) {
			BigInteger productHolder = new BigInteger();
			while(secondNode!=null) {
				int product =(firstNode.digit*secondNode.digit)+carry;
				if(product>=10) {
					carry=product/10;
					product=product%10;
					
				}
				else {
					carry = 0;
				}
				if(productHolder.numDigits==0) {
					productHolder.front= new DigitNode(product,productHolder.front);
				}
				else {
					DigitNode temp = productHolder.front;
					while(temp.next!=null) {
						temp=temp.next;
					}
					temp.next= new DigitNode(product,null);
				}
				productHolder.numDigits++;
				secondNode=secondNode.next;
			}
			int numZeros2 = numZeros;
			while(numZeros2>0) {
				productHolder.front = new DigitNode(0,productHolder.front);
				productHolder.numDigits++;
				numZeros2--;
			}
			if(carry>0) {
				DigitNode temp2 = productHolder.front;
				while(temp2.next!=null) {
					temp2=temp2.next;
				}
				temp2.next= new DigitNode(carry,null);
				productHolder.numDigits++;
			}
			productHolder =BigInteger.parse(productHolder.toString());
			pie3 = pie3.add(pie3, productHolder);
			numZeros++;
			carry=0;
			firstNode=firstNode.next;
			secondNode=second.front;
		
		}
		if(first.negative==true&&second.negative==true) {
			pie3.negative=false;
		}
		if(first.negative==true&&second.negative==false||second.negative==true&&first.negative==false) {
			pie3.negative=true;
		}
		if(first.negative==false&&second.negative==false) {
			pie3.negative=false;
		}

		return pie3;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (front == null) {
			return "0";
		}
		String retval = front.digit + "";
		for (DigitNode curr = front.next; curr != null; curr = curr.next) {
				retval = curr.digit + retval;
		}
		
		if (negative) {
			retval = '-' + retval;
		}
		return retval;
	}
}
