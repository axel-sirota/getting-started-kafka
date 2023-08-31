INSTANCE=$1
CA_ALIAS="ca-root"
CA_CERT_FILE="ca-cert" 

#### Generate Truststore and import ROOT CA certificate ####
keytool -keystore truststore/$INSTANCE.truststore.jks -import -alias $CA_ALIAS -file $CA_CERT_FILE
