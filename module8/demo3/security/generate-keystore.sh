COMMON_NAME=$1
ORGANIZATIONAL_UNIT="Community"
ORGANIZATION="Pluralsight"
CITY="Utah"
STATE="Utah"
COUNTRY="US"

CA_ALIAS="ca-root"
CA_CERT_FILE="ca-cert" 
VALIDITY_DAYS=36500

# Generate Keystore with Private Key
keytool -keystore keystore/$COMMON_NAME.keystore.jks -alias $COMMON_NAME -validity $VALIDITY_DAYS -genkey -keyalg RSA -dname "CN=$COMMON_NAME, OU=$ORGANIZATIONAL_UNIT, O=$ORGANIZATION, L=$CITY, ST=$STATE, C=$COUNTRY"

# Generate Certificate Signing Request (CSR) using the newly created KeyStore
keytool -keystore keystore/$COMMON_NAME.keystore.jks -alias $COMMON_NAME -certreq -file $COMMON_NAME.csr 

# Sign the CSR using the custom CA
openssl x509 -req -CA ca-cert -CAkey ca-key -in $COMMON_NAME.csr -out $COMMON_NAME.signed -days $VALIDITY_DAYS -CAcreateserial

# Import ROOT CA certificate into Keystore
keytool -keystore keystore/$COMMON_NAME.keystore.jks -alias $CA_ALIAS -importcert -file $CA_CERT_FILE

# Import newly signed certificate into Keystore
keytool -keystore keystore/$COMMON_NAME.keystore.jks -alias $COMMON_NAME -importcert -file $COMMON_NAME.signed

# Clean-up 
rm $COMMON_NAME.csr
rm $COMMON_NAME.signed
rm ca-cert.srl