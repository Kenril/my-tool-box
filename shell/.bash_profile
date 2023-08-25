export repo-perso="${HOME}/Desktop/PERSO/repos"
export toolbox="${repo-perso}/my-tool-box"

source "${toolbox}/git/git_global_cmds.sh"
source "${toolbox}/jahia/provisioning-available-module-list.sh"
source "${toolbox}/jahia/provisioning-generate-script.sh"

# --show-control-chars: help showing Korean or accented characters
alias ls='ls -F --color=auto --show-control-chars'
alias ll='ls -l'

alias reload='source ~/.bash_profile'
alias cd..='cd ..'

# Git related
alias git-all="${toolbox}/git/git_global_cmds.sh"
alias ga='git-all'

alias gaprune='git-all prune'
alias gafetch='git-all fetch'
alias gapull='git-all pull'
alias gadev='git-all check-dev'
alias gamaster='git-all check-master'


# Jahia utility scripts
alias jahia-prov-list="${toolbox}/jahia/provisioning-available-module-list.sh" # Generates a list of
alias jahia-prov-create="${toolbox}/jahia/provisioning-generate-script.sh"

alias jahia-pl='jahia-prov-list'
alias jahia-pc='jahia-prov-create'


# For special applications
case "$TERM" in
xterm*)
	# The following programs are known to require a Win32 Console
	# for interactive usage, therefore let's launch them through winpty
	# when run inside `mintty`.
	for name in node ipython php php5 psql python2.7
	do
		case "$(type -p "$name".exe 2>/dev/null)" in
		''|/usr/bin/*) continue;;
		esac
		alias $name="winpty $name.exe"
	done
	;;
esac