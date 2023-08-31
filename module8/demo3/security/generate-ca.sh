VALIDITY_DAYS=36500
CA_KEY_FILE="ca-key"
CA_CERT_FILE="ca-cert" 

openssl req -new -x509 -keyout $CA_KEY_FILE -out $CA_CERT_FILE -days $VALIDITY_DAYS

#### Example Values ####
# Passphrase: password
# Country Name: US
# State or Province: UT
# City: Utah
# Organization Name: Pluralsight
# Organizational Unit Name: Community
# Common Name: pluralsight.com
# Email: learner@pluralsight.com