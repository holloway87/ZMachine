package com.me_hw.z_machine;

import java.io.File;

/**
 * Executes the Z Machine.
 *
 * @author Thomas Rudolph <me@holloway-web.de>
 * @since 2014.12.01
 */
public class Executor
{

	/**
	 * Main entry point.
	 *
	 * @param args program arguments
	 */
	public static void main(String[] args)
	{
		File storyfile = new File(args[0]);
		ZMachine machine = new ZMachine();

		machine.loadStoryfile(storyfile);
	}

}
