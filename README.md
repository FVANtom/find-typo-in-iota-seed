# WARNING

NEVER give your seed to anyone, not even to someone you think you can trust. Not even the devs. There's scammers out there. NEVER enter your seed into or download any software that is closed source and/or that hasn't been reviewed and approved by either the IOTA foundation or at least the IOTA community.


## What does this tool do

I have written this tool to help a user on Reddit who accidentally made a typo in his seed while logging in into the wallet.

He then transfered iota to the address he generated and logged out of his wallet.

Because he accidentally made a typo when logging in, he lost access to his funds.

This program checks the most common typing mistakes and tries to find the real seed (typo-seed) of the address the funds were transferred to.

* Generating seeds with one missing character readded
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

## How to build and run:

    mvn clean install

    java -jar target/find-typo-in-iota-seed-jar-with-dependencies.jar 
    
## How to run the precompiled binary

Download the latest release at 
https://github.com/FVANtom/find-typo-in-iota-seed/releases

You will need to have at least java 8 installed:
https://java.com/en/download/

Download the jar and run it with the following command:

    java -jar find-typo-in-iota-seed-jar-with-dependencies.jar
    
## No idea how to use this tool?

If you are not able to run this tool yourself, or you need customization. I am willing to try and help you. 

My name is Tom
 - You can email me at iota@terranovita.com 
 - Alternatively you can contact 'fvantom' on the IOTA Slack chat.
 - Or message FVANtom333 on Reddit (https://www.reddit.com/user/FVANtom333/)

## Make a donation

If you have recovered your funds using this tool, please think of my children and make a donation ;-)

My iota address: 

    ELDM9DAWZVHNIQQROREDKPUJRZKMZCRKROSGQXROZCYSQJKKTRNHNEBKNRSAJXOUZBGKUFWTNTN9VKTBWYJFDAQNOD

## Add new version to maven local repo

Download the jar with depencencies from the official java library at https://github.com/iotaledger/iota.lib.java

    mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file \
      -Dfile=./jota-VERSION-HERE-jar-with-dependencies.jar \
      -DgroupId=iota.lib.java \
      -DartifactId=jota \
      -Dversion=VERSION \
      -Dpackaging=jar \
      -DlocalRepositoryPath=lib
      
Or you can switch to the latest maven iota java library

## License

Copyright 2017 TerraNovita Software BVBA

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
