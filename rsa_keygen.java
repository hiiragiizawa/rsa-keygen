import java.io.Writer;
import java.io.FileWriter;
import java.io.OutputStreamWriter;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import java.util.Base64;

public class rsa_keygen
{
    static private Base64.Encoder encoder = Base64.getEncoder();

    static private void writeBase64(Writer out,Key key) throws java.io.IOException
    {
		byte[] buf = key.getEncoded();
		out.write(encoder.encodeToString(buf));
		out.write("\n");
    }

    static public void main(String[] args) throws Exception
    {
		if ( args.length == 0 ) {
			System.out.println("Example usage:");
			System.out.println("  rsa_keygen [filename]");
			System.exit(1);
		}

		String algo = "rsa"; // default to RSA key generation algorithm
		int keySize = 4096; // initializing RSA with keySize of 4096

		String outFile = args[0];

		KeyPairGenerator kpg = KeyPairGenerator.getInstance(algo);
		kpg.initialize(keySize);
		KeyPair kp = kpg.generateKeyPair();

		System.out.println("Generating private key, format: " + kp.getPrivate().getFormat());
		// Begin writing to file
		Writer private_key = new FileWriter(outFile + ".key");
		private_key.write("-----BEGIN RSA PRIVATE KEY-----\n");
		writeBase64(private_key, kp.getPrivate());
		private_key.write("-----END RSA PRIVATE KEY-----\n");
		private_key.close();
		// end writing
		System.out.println("Your private key has been saved in " + outFile + ".key");
		

		
		System.out.println("Generating public key, format: " + kp.getPublic().getFormat());
		// Begin writing to file
		Writer public_key = new FileWriter(outFile + ".pub");
		public_key.write("-----BEGIN RSA PUBLIC KEY-----\n");
		writeBase64(public_key, kp.getPublic());
		public_key.write("-----END RSA PUBLIC KEY-----\n");
		public_key.close();
		// End writing
		System.out.println("Your private key has been saved in " + outFile + ".pub");
		
    }
}
