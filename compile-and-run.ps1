# Understanding compilation:  http://tutorials.jenkov.com/java/java-project-overview-compilation-and-execution.html
# Understanding class-path: https://docs.oracle.com/javase/7/docs/technotes/tools/windows/classpath.html

cd "$PSScriptRoot"

$javaFiles = $(Get-ChildItem -Recurse -Filter '*.java')
For($i=0; $i -le $javaFiles.count - 1; $i++)
{
    $javaFiles[$i] = $(Resolve-Path -Relative $javaFiles[$i].FullName)
}

javac -sourcepath src -cp "./core.jar" -d classes $javaFiles 

java -cp "./core.jar;./classes" main.MainApplication

Read-Host "Press 'enter' to exit"
