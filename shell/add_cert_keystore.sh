#!/bin/bash

# $1 certificate file to import
# $2 keystore file in which the certificate will be added
# $3 name of the certificate in the keystore (display name)

keytool -importcert -file $1 -keystore $2 -alias $3