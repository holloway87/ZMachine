package com.me_hw.z_machine;

/**
 * Represents the memory of the story file.
 *
 * Provides methods to read from the memory at a given address.
 *
 * @author Thomas Rudolph <me@holloway-web.de>
 * @since 2014.12.01
 */
public class Memory
{

	/**
	 * The length of the dynamic memory givn in the header position 0x0e.
	 */
	private int dynamicLength;

	/**
	 * The length of the file given in the header position 0x1a.
	 *
	 * @since 2014.12.02
	 */
	private int fileLength;

	/**
	 * The original unsigned bytes buffer from the story file.
	 *
	 * @since 2014.12.02
	 */
	private int[] origBuffer;

	/**
	 * The address for the first routine given in the header position 0x06.
	 *
	 * @since 2014.12.02
	 */
	private int startAddr;

	/**
	 * The version file from the story file.
	 */
	private int version;

	/**
	 * Saves the buffer and reads the header information.
	 *
	 * @param buffer all bytes from the story file
	 */
	Memory(int[] buffer)
	{
		origBuffer = buffer;

		_readVersion();
		_readDymanicLength();
		_readFileLength();
		_readStartAddr();
	}

	/**
	 * Determines the number of packs from the version number.
	 *
	 * @since 2014.12.02
	 * @return the number of packs
	 */
	private int _getPacks()
	{
		int packs = 0;
		switch (version)
		{
			case 1:
			case 2:
			case 3:
				packs = 2;
				break;
			case 4:
			case 5:
				packs = 4;
				break;
			case 8:
				packs = 8;
				break;
		}

		return packs;
	}

	/**
	 * Reads the version number.
	 */
	private void _readVersion()
	{
		version = getByteAtAddr(0x0);
	}

	/**
	 * Reads the dynamic memory length from the header position 0x0e.
	 */
	private void _readDymanicLength()
	{
		dynamicLength = get2BytesAtAddr(0x0e) * _getPacks();
	}

	/**
	 * Reads the file length from the header position 0x1a.
	 *
	 * @since 2014.12.02
	 */
	private void _readFileLength()
	{
		fileLength = get2BytesAtAddr(0x1a) * _getPacks();
	}

	/**
	 * Reads the start address for the first routing from the header position 0x06.
	 *
	 * @since 2014.12.02
	 */
	private void _readStartAddr()
	{
		startAddr = getByteAtAddr(0x06) * _getPacks();
	}

	/**
	 * Returns the byte at the given address.
	 *
	 * @param addr address
	 * @return byte data
	 */
	int getByteAtAddr(int addr)
	{
		return origBuffer[addr];
	}

	/**
	 * Returns the version number.
	 *
	 * @return version number
	 */
	int getVersion()
	{
		return version;
	}

	/**
	 * Returns the value of 2 bytes at the given address.
	 *
	 * @param addr address
	 * @return 2 bytes data
	 */
	int get2BytesAtAddr(int addr)
	{
		return (getByteAtAddr(addr) << 8) + getByteAtAddr(addr +1);
	}

	/**
	 * Returns the dynamic memory length.
	 *
	 * @return dynamic memory length
	 */
	int getDynamicLength()
	{
		return dynamicLength;
	}

	/**
	 * Returns the file length.
	 *
	 * @since 2014.12.02
	 * @return file length
	 */
	int getFileLength()
	{
		return fileLength;
	}

	/**
	 * Returns the memory length.
	 *
	 * @return memory length
	 */
	int getLength()
	{
		return origBuffer.length;
	}

	/**
	 * Returns the start address for the first routine.
	 *
	 * @since 2014.12.02
	 * @return start address
	 */
	int getStartAddr()
	{
		return startAddr;
	}

	/**
	 * Returns the value of x bytes at the given address.
	 *
	 * @param addr address
	 * @param x how many bytes
	 * @return x bytes data
	 */
	int getXBytesAtAddr(int addr, int x)
	{
		int data = 0;
		for (int c = 0; c < x; c++)
		{
			data = (data << 8) + getByteAtAddr(addr + c);
		}
		return data;
	}

}
