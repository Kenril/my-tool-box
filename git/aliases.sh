# Some good standards, which are not used if the user
# creates his/her own .bashrc/.bash_profile

# --show-control-chars: help showing Korean or accented characters
alias ls='ls -F --color=auto --show-control-chars'
alias ll='ls -l'

alias gitall='./git_global_cmds.sh'
alias ga='gitall'

alias gaprune='git-all prune'
alias gafetch='git-all fetch'
alias gapull='git-all pull'
alias gadev='git-all check-dev'
alias gamaster='git-all check-master'