## Using git_global_cmds.sh
`./git_global_cmds.sh pull|fetch|prune`

The script will loop through all the sub folders of the folder from where the script was executed.

It will then, in each sub-folder, execute the git commande given to the script.

### pull
Will update the currently checked out branch of each sub-folder. 

### fetch | prune
The option `fetch` and `prune` do the same.

Will update the local repository's information with the information from the remote (like any good old git fetch) <br>
and delete **ANY** branch that doesn't existe on the remote repository.

If you have a local branche with changes that you have not pushed **it will disappear**.

### Debugging
It is possible to get more logs out of the script if debugging is needed by editing the value of the var `DEBUG` in the script directly.  

## Using git_prune_local.sh
`./git_prune_local.sh` in a git repository folder will delete all local branches that don't existe on remote.

## Delete all local tags
git tag -d $(git tag -l)