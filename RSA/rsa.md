---
title: 'RSA encryption and decryption'
author: 'SST Computer Club'
date: 'September 6, 2018'
slideNumber: true
css: ['custom.css']
center: 'false'
revealjs-url: '../reveal.js'
---

##Overview of RSA
- RSA (Rivest-Shamir-Adleman)
- Public-key cryptosystem
    - Encryption and decryption key are unique
- Asymmetric
    - Based on the difficulty of prime factorization

##Using RSA
- User creates a public and a private key
![](images/rsafig.jpg)

##Key generation
![](https://wikimedia.org/api/rest_v1/media/math/render/svg/98b7f857d10d5b056d5db624bd3a475d2cb475cd)
- 2 distinct prime numbers, p and q
- pq = n
    - n is the modulus for both keys
    - n's length is the key length (in bits)

