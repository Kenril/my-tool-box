#!/bin/bash

# Author Julien CHARLES
# Contributor Erik MUNK

M2_DIR="${HOME}/.m2/repository"
file=${1}

sitekey=$(basename "${file}" .zip)

pathToFolderWithExportedSite=$(dirname "${file}")

function generate {
	echo '- addMavenRepository: "https://mvnrepository.com"'
	# echo '  username: "user"' # if user password is needed because prive maven repo
	# echo '  password: "mdp"'
	echo '- installOrUpgradeBundle:'
	local id
	for id in $(zgrep installedModules ${file} | sed "s|^.*=||g")
	do
	   local module
	   module=$(grep "${id}" "${M2_DIR}/available-jahia-modules.list")
	   if [[ ${module} ]]
	   then
		 echo "  - \"mvn:${module}\""
	   else
		 echo "  - ${id}"
	   fi
	done
	echo '  autoStart: true'
	echo "- importSite: 'file:${pathToFolderWithExportedSite}\\${sitekey}.zip'"
	echo
}

generate > "${sitekey}".yaml
