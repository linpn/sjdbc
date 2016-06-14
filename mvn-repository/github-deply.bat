del com/

cd ../sjdbc/
call mvn -DaltDeploymentRepository=snapshot-repo::default::file:../mvn-repository clean deploy

@pause