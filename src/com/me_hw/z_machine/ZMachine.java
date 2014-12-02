package com.me_hw.z_machine;

import java.io.*;

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
	 * Executes the z program from the loaded story file.
	 *
	 * @since 2014.12.02
	 */
	void execute()
	{
		int startByte = memory.getByteAtAddr(memory.getStartAddr());

		System.out.printf("storyfile length: %d\n", memory.getLength());
		System.out.printf("dynamic memory length: %d\n", memory.getDynamicLength());
		System.out.printf("startByte: %d\n", startByte);
		System.out.printf("fileLength: %d\n", memory.getFileLength());
	}

	/**
	 * Loads the given story file into the memory.
	 *
	 * @param file story file
	 */
	void loadStoryfile(File file) throws IOException
	{
		int[] buffer = new int[(int)file.length()];
		InputStream in;
		DataInputStream stream;

		// load input stream
		try
		{
			in = new FileInputStream(file);
			stream = new DataInputStream(in);
		}
		catch (FileNotFoundException e)
		{
			return;
		}

		// read bytes
		try
		{
			for (int c = 0; c < file.length(); c++)
			{
				buffer[c] = stream.readUnsignedByte();
			}
		}
		catch (EOFException e)
		{
			stream.close();
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
	}

}
