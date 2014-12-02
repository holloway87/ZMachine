package com.me_hw.z_machine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Represents the Z Machine.
 *
 * Loads and executes story files.
 *
 * @author Thomas Rudolph <me@holloway-web.de>
 * @since 2014.12.01
 */
public class ZMachine
{

	/**
	 * The memory instance.
	 */
	private Memory memory;

	/**
	 * The program counter.
	 */
	private int pc;

	/**
	 * Loads the given story file into the memory.
	 *
	 * @param file story file
	 */
	void loadStoryfile(File file)
	{
		byte[] buffer = new byte[(int)file.length()];
		InputStream in;

		// load input stream
		try
		{
			in = new FileInputStream(file);
		}
		catch (FileNotFoundException e)
		{
			return;
		}

		// read bytes
		try
		{
			in.read(buffer);
			in.close();
		}
		catch (IOException e)
		{
			return;
		}

		// save memory
		try
		{
			memory = new Memory(buffer);
		}
		catch (Exception e)
		{
			return;
		}
		pc = 0;
		System.out.printf("storyfile length: %d\n", memory.getLength());
		System.out.printf("dynamic memory length: %d\n", memory.getDynamicLength());
	}

}
