


## Important Concepts
Repudiation - by using diffenrent private keys,
you can tell who wrote something.

### PKI - Public Key Infrastructure
- Public Key
	- Shared and known
	- Anyone can look it up
	- Used by others to sign things
- Private Key
	- Not shared
	- The **decrypt is for you** and **encrypt for other**
- Certificate - contains identifying information 
	- They are signed with the keys, making it possible to be validated

### Public Key Cryptosystem - PKCS
- TODO Paste image from wikipedia

### Algorithms and Bits
- Algorithm is how something is encrypted/decrypted
	- Ex: RSA or 3DES
- Bits represent the strenght of the algorithm
	- Often move in powers of 2 (block size)
	- Ex: RSA is 2048 bits

## Symmetric Ciphers
- Fast encryption and decryption
- **Same key does both encryption and decryption**
	- Anyone who can encrypt => can decrypt

### Popular Symmetric Algorithms
- AES
- 3DES 
- DES [Old - It was broken]
- Blowfish
- Serpent

### How Does Symmetric Ciphers Work?
- Someone uses a password to encrypt information
- The information is now illegible to others
- The encrypted data goes to the receiver
- Form may change. An example is spoken to written.
- The receiver uses the same password to decrypt the message
- The information is legible only with this password

### Why is not symettric good for everything?
You cannot use repudiation to determine who wrotes the message. The hability to determine which side of a communication made a certain part of the message is really important in a scenerio that involves the internet.

### Importat terms
- Initialization Vector - Kicks off the encryption
- Key - the unit that will encrypt/decrypt data

### Canculating Keys and IVs
- Key is measured in bits
	- A byte[16] key is a 128 key
		- 8 bits * 16 bytes
- IV is measured in bytes 

## Asymmetric Ciphers

### Popular Asymmetric Algorithms
- RSA
- Elliptical curve
- Diffie-Hellman   
