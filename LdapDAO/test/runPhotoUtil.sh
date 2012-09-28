#!/bin/sh
#

CP=
for i in `ls ../../AdapterKMRLib/springldap/*.jar`
do
  CP="${CP};${i}"
done
for i in `ls ../../../../../ThirdParty/log4j/*.jar`
do
  CP="${CP};${i}"
done
CP="${CP};../../KMRCommonLib/dist/KMRCommonLib.jar"


java -cp "../dist/LdapDAO.jar;${CP}" gov.hhs.fha.nhinc.ldaputil.PhotoUploadUtil $*
