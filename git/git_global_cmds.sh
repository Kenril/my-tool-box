#!/usr/bin/env bash

GIT_CMD=${*};
DEBUG=false;
QUIET_OPTS="-q"

GIT_PRUNE_ALIAS=$(source git_prune_local.sh)


if [ "${DEBUG}" == "verbose" ]; then
  QUIET_OPTS=""
fi

function debug() {
  if [ "${DEBUG}" == "true" ] || [ "${DEBUG}" == "verbose" ]; then
    echo "${1}"
  fi
}

function findGitCmd() {
  debug "Finding git cmd for ${1}"
    if [[ "${1}" == *"pull"* ]]; then
      GIT_CMD_ALIAS='git pull '"${QUIET_OPTS}"
    fi
    if [[ "${1}" == *"fetch"* ]]; then
      GIT_CMD_ALIAS=${GIT_PRUNE_ALIAS}
    fi
    if [[ "${1}" == *"prune"* ]]; then
      GIT_CMD_ALIAS=${GIT_PRUNE_ALIAS}
    fi
    if [[ "${1}" == *"check-dev"* ]]; then
      GIT_CMD_ALIAS='git checkout develop'
    fi
    if [[ "${1}" == *"check-master"* ]]; then
      GIT_CMD_ALIAS='git checkout master'
    fi
}

function execute() {
  echo "Executing git ${GIT_CMD} in ${1}"
  debug "3 : cd into ${1}"
  cd "${1}" || exit 50
  if [ "pull" == "${GIT_CMD}" ]; then
    git stash ${QUIET_OPTS}
  fi

  findGitCmd ${GIT_CMD}

  ${GIT_CMD_ALIAS} || exit
  echo "Finished ${GIT_CMD} in ${1}"

  if [[ "${GIT_CMD}" == *"fetch"* ]]; then
    debug "Executing git gc..."
    git gc ${QUIET_OPTS};
  fi

  if [ "pull" == "${GIT_CMD}" ]; then
    git stash pop ${QUIET_OPTS}
  fi
  debug "3.1 : cd up"
  cd ..
}

function isGitDirectory() {
  debug "Start isGitDirectory function"
  directory=${1};
  if [ -f "${directory}/.gitignore" ]; then
    isGitDir="true";
  else
    isGitDir="false";
  fi
}

function loop() {
  for possibleDirectory in ${1}
  do
    if [[ "${possibleDirectory}" == "./idea" || "${possibleDirectory}" == "./idea/*" ]]; then
      # ignore ./idea folder and subfolders
      continue
    fi
    isGitDirectory "${possibleDirectory}";
    if [ "${possibleDirectory}" == "./*/" ]; then
      continue;
    elif [ ${isGitDir} == "false" ]; then
      debug "2 : cd into ${possibleDirectory}"
      cd "${possibleDirectory}" || exit 49;
      for subDirectory in ./*/
      do
        debug "2.1 : Starting loop ${subDirectory}"
        loop "${subDirectory}"
        debug "2.2 : End loop ${subDirectory}"
      done
      debug "2.3 : cd up"
      cd ..
    else
      execute "${possibleDirectory}"
    fi
  done
}

echo "Starting git ${GIT_CMD}..."
debug "1 : cd into $( dirname -- "$0"; )"
cd "$( dirname -- "$0"; )" || exit 49;
isGitDir="false";

for d in ./*/
do
  debug "0 : Starting loop ${d}"
  loop "${d}"
  debug "0.1 : End loop ${d}"
done

echo "Finished all"

