# WARNING

NEVER give someone you do not trust 100% your seed and never enter your seed in software you do not trust. Under the hood such software can send your seed to someone who wishes to steal your funds.

## What does this tool do

I have written this script to help a user on Reddit who accidentally made a typo in his seed while logging in into the wallet.

He then transfered iota to the address he generated and logged out of his wallet.

Because he accidentally made a typo when logging in, he lost access to his funds.

This program checks the most common typing mistakes and tries to find the seeds the funds were actually transferred to.

* Generating seeds with one character missing
* Generating seeds with character typed double
* Generating seeds with letters that look like each other replaced
* Generating seeds with block of 2 character retyped
* Generating seeds with 2 neighbouring characters switched
* Generating seeds with 1 character mistyped

### Who is this program useful for

Anyone who transferred funds to an IOTA address of a seed similar to his own but with a typo and doesn't know the 'typo-seed'.

### To use this program you will need:

* Your original seed
* The address your funds were transfered (belonging to the 'typo-seed')

## How to run:

    mvn clean install

    java -jar target/find-typo-in-iota-seed-jar-with-dependencies.jar 
    
## No idea how to use this tool?

If you are not able to run this tool yourself, or you need customization. I am willing to try and help you.
You can email me at iota@terranovita.com (my name is Tom)

## Make a donation

If you have recovered your funds using this tool, please think of my children and make a donation ;-)

My iota address: 

    ELDM9DAWZVHNIQQROREDKPUJRZKMZCRKROSGQXROZCYSQJKKTRNHNEBKNRSAJXOUZBGKUFWTNTN9VKTBWYJFDAQNOD

## Add new version to maven local repo

    mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file \
      -Dfile=./jota-VERSION-HERE-jar-with-dependencies.jar \
      -DgroupId=iota.lib.java \
      -DartifactId=jota \
      -Dversion=VERSION \
      -Dpackaging=jar \
      -DlocalRepositoryPath=lib
      
Or you can switch to the latest iota java library
https://github.com/iotaledger/iota.lib.java

## License

Copyright 2017 TerraNovita Software BVBA

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
