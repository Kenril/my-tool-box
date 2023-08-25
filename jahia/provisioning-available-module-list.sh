#!/bin/bash

# Author Julien CHARLES
# Contributor Erik MUNK

M2_DIR="${HOME}/.m2/repository"
JAHIA_GROUP='org/jahia'

echo "" > ${M2_DIR}/available-jahia-modules.list || exit 50

(find ${M2_DIR} -type d -regex "${M2_DIR}/${JAHIA_GROUP}/[^/]*/[^/]*" ;) |
  sed 's|'"${M2_DIR}"'\/||' |
  sed 's|\/|.|g' |
  sed 's|\.\([^.]*\)$|\/\1|' > ${M2_DIR}/available-jahia-modules.list

cat ${M2_DIR}/available-jahia-modules.list
