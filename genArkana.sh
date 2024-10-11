checkAndExecute() {
  if [ -z $1 ]
  then
    echo 'Please run "./genArkana.sh FLAVOR_NAME"'
    return 1
  fi

  execute $1
}

execute() {
  bundle exec arkana -l kotlin --flavor $1 --config-filepath .arkana.yml
  cd buildSrc
  bundle exec arkana -l kotlin --config-filepath .arkana.yml
  cd ..
}

checkAndExecute $1