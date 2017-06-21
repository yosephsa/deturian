/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Generator;

import java.util.LinkedList;
import java.util.Random;

/**
 *
 * @author Yoseph Alabdulwahab
 */
public final class SteppableRandom{
	
	private final int inc = 100;
	
	private long seed;
	
	private int longIndex = 0, intIndex = 0, doubleIndex = 0, floatIndex, booleanIndex = 0;
	private int longMax = 0, intMax = 0, doubleMax = 0, floatMax = 0, booleanMax = 0;
	
	private LinkedList<Long> longValues;
	private LinkedList<Integer> intValues;
	private LinkedList<Double> doubleValues;
	private LinkedList<Float> floatValues;
	private LinkedList<Boolean> booleanValues;
	
	Random ranLong, ranInt, ranDouble, ranFloat, ranBoolean;
	
	/**
	 * initializes SteppableRandom. sets seed to currentTimeMillis().
	 */
	public SteppableRandom()
	{
		seed = System.currentTimeMillis();
		
		ranLong = new Random(seed);
		ranInt = new Random(seed);
		ranDouble = new Random(seed);
		ranFloat = new Random(seed);
		ranBoolean = new Random(seed);
		
		longValues = new LinkedList<>();
		intValues = new LinkedList<>();
		doubleValues = new LinkedList<>();
		floatValues = new LinkedList<>();
		booleanValues = new LinkedList<>();
		setSeed(seed);
	}
	
	/**
	 * initializes SteppableRandom.
	 * @param seed a seed to use in generating sudorandom values.
	 */
	public SteppableRandom(long seed)
	{
		ranLong = new Random(seed);
		ranInt = new Random(seed);
		ranDouble = new Random(seed);
		ranFloat = new Random(seed);
		ranBoolean = new Random(seed);
		
		longValues = new LinkedList<>();
		intValues = new LinkedList<>();
		doubleValues = new LinkedList<>();
		floatValues = new LinkedList<>();
		booleanValues = new LinkedList<>();
		setSeed(seed);
	}
	
	/**
	 * resets the counters and limiters. sets the current seed to given seed. generates first increment sized list of values.
	 * @param seed a seed to make sudorandom values.
	 */
	public void setSeed(long seed)
	{
		this.seed = seed;
		ranFloat.setSeed(seed);
		ranBoolean.setSeed(seed);
		ranDouble.setSeed(seed);
		ranInt.setSeed(seed);
		ranLong.setSeed(seed);
		longValues.clear();
		intValues.clear();
		doubleValues.clear();
		floatValues.clear();
		booleanValues.clear();
		longMax = intMax = doubleMax = floatMax = booleanMax = inc;
		longIndex = intIndex = doubleIndex = floatIndex = booleanIndex = 0;
		preGen();
	}
	
	/**
	 * increases the forsight on every datatype by the incrementing value. 
	 */
	public void preGen()
	{
		preGenLong();
		preGenInt();
		preGenDouble();
		preGenFloat();
		preGenBoolean();
	}
	
	/**
	 * increases forsight for long variables only by incrementing value.
	 */
	public void preGenLong()
	{
		longMax += inc;
		for(int i = longIndex; i < longMax; i++)
		{
			longValues.add(ranLong.nextLong());
		}
	}
	
	/**
	 * increases forsight for int variables only by incrementing value.
	 */
	public void preGenInt()
	{
		intMax += inc;

		for(int i = intIndex; i < intMax; i++)
		{
			intValues.add(ranInt.nextInt());
		}
	}
	
	/**
	 * increases forsight for double variables only by incrementing value.
	 */
	public void preGenDouble()
	{
		doubleMax += inc;
		for(int i = doubleIndex; i < doubleMax; i++)
		{
			doubleValues.add(ranDouble.nextDouble());
		}
	}
	
	/**
	 * increases forsight for float variable only by incrementing value.
	 */
	public void preGenFloat()
	{
		floatMax += inc;
		for(int i = floatIndex; i < floatMax; i++)
		{
			floatValues.add(ranFloat.nextFloat());
		}
	}
	
	/**
	 * increases forsight for boolean variables only by incrementing value.
	 */
	public void preGenBoolean()
	{
		booleanMax += inc;
		for(int i = booleanIndex; i < booleanMax; i++)
		{
			booleanValues.add(ranBoolean.nextBoolean());
		}
	}
	
	/**
	 * 
	 * @return next sudorandom long in the list of sudorandom longs.
	 */
	public long nextLong()
	{
		int i = longIndex;
		longIndex++;
		if(longIndex == longMax)
			preGenLong();
		return longValues.get(i);
	}
	
	/**
	 * 
	 * @return next sudorandom Integer in list of sudorandom integers.
	 */
	public int nextInt()
	{
		int i = intIndex;
		intIndex++;
		if(intIndex == intMax)
			preGenInt();
		return intValues.get(i);
	}
	
	/**
	 * 
	 * @return next sudorandom double in list of sudorandom integers.
	 */
	public double nextDouble()
	{
		int i = doubleIndex;
		intIndex++;
		if(doubleIndex == doubleMax)
			preGenDouble();
		return doubleValues.get(i);
	}
	
	/**
	 * 
	 * @return next sudorandom float in list of sudorandom floats.
	 */
	public float nextFloat()
	{
		int i = floatIndex;
		floatIndex++;
		if(floatIndex == floatMax)
			preGenFloat();
		return floatValues.get(i);
	}
	
	/**
	 * 
	 * @return next sudorandom boolean in list of sudorandom booleans.
	 */
	public boolean nextBoolean()
	{
		int i = booleanIndex;
		booleanIndex++;
		if(booleanIndex == doubleMax)
			preGenDouble();
		return booleanValues.get(i);
	}
	
	/**
	 * 
	 * @param i position in sudorandom list of longs.
	 * @return long from sudorandom list of longs.
	 */
	public long longAt(int i)
	{
		if(i >= longMax)
			for(int j = 0; j < (i - longMax) / inc + 1; j++)
				preGenLong();
		return longValues.get(i);
	}
	
	/**
	 * 
	 * @param i position in sudorandom list of integers.
	 * @return integer from sudorandom list of integers.
	 */
	public int intAt(int i)
	{
		if(i >= intMax)
			for(int j = 0; j < (i - intMax) / inc + 1; j++)
				preGenInt();
		return intValues.get(i);
	}
	
	/**
	 * 
	 * @param i position in sudorandom list of doubles.
	 * @return double from sudorandom list of doubles.
	 */
	public double doubleAt(int i)
	{
		if(i >= doubleMax)
			for(int j = 0; j < (i - doubleMax) / inc  + 1; j++)
				preGenDouble();
		return doubleValues.get(i);
	}
	
	/**
	 * 
	 * @param i position in sudorandom list of floats.
	 * @return float from sudorandom list of floats.
	 */
	public float floatAt(int i)
	{
		if(i >= floatMax)
			for(int j = 0; j < (i - floatMax) / inc + 1; j++)
				preGenFloat();
		return floatValues.get(i);
	}
			
	/**
	 * 
	 * @param i position in sudorandom list of booleans.
	 * @return boolean from sudorandom list of booleans.
	 */
	public boolean booleanAt(int i)
	{
		if(i >= booleanMax)
			for(int j = 0; j < (i - booleanMax) / inc + 1; j++)
				preGenBoolean();
		return booleanValues.get(i);
	}
	
	
	
	
}
