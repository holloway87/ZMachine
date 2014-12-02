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
	 * The length of the dynamic memory.
	 */
	private int dynamicLength;

	/**
	 * The original bytes buffer from the story file.
	 */
	private byte[] origBuffer;

	/**
	 * The version file from the story file.
	 */
	private int version;

	/**
	 * Saves the buffer and reads the header information.
	 *
	 * @param buffer all bytes from the story file
	 * @throws Exception
	 */
	Memory(byte[] buffer) throws Exception
	{
		origBuffer = buffer;
		if (64 > origBuffer.length)
		{
			throw new Exception();
		}

		_readVersion();
		_readDymanicLength();
	}

	/**
	 * Reads the version number.
	 */
	void _readVersion()
	{
		version = (int)getByteAtAddr(0x0);
	}

	/**
	 * Reads the dynamic memory length.
	 */
	void _readDymanicLength()
	{
		dynamicLength = get2BytesAtAddr(0x0e);
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
		dynamicLength *= packs;
	}

	/**
	 * Returns the byte at the given address.
	 *
	 * @param addr address
	 * @return byte data
	 */
	byte getByteAtAddr(int addr)
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
		return (getByteAtAddr(addr) << 8) + (int)getByteAtAddr(addr +1);
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
	 * Returns the memory length.
	 *
	 * @return memory length
	 */
	int getLength()
	{
		return origBuffer.length;
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
			data = (data << 8) + (int)(getByteAtAddr(addr + c));
		}
		return data;
	}

}
