#!/usr/bin/env python

import sys
import os
import copy
import fnmatch
import re

version = "1.0"
banner_msg = "Evaluador para el Proyecto 2, ver. " + version + "."
help_msg = banner_msg + "\n\n\
El script compila las fuentes en java, corre el proyecto sobre los\n\
casos de prueba e imprime estadisticas de los resultados. Sintaxis:\n\n\
  evaluate timeout Nota <camino-a-repositorio-de-casos-de-prueba>+\n\n\
  evaluate Nota <camino-a-repositorio-de-casos-de-prueba>+\n\n\
  evaluate  <camino-a-repositorio-de-casos-de-prueba>+\n\n\
El repositorio contiene los casos de prueba y sus soluciones. Cada\n\
caso de prueba debe tener extension \".in\" y su solucion debe\n\
tener extension \".out\".\n"

tmp_dir = "tmp.results.p2"

def banner():
  print banner_msg + "\n\n"

def usage():
  print help_msg

def clean():
  os.system("rm -fr *.class *~ %s" % tmp_dir)

def populate_tmp_dir(path,tmp_dir):
  if os.path.isdir(path):
    ext_tmp_dir = tmp_dir + "/" + os.path.basename(path)
    os.system("mkdir %s" % ext_tmp_dir)
    for file in os.listdir(path):
      ext_path = path + "/" + file
      populate_tmp_dir(ext_path,ext_tmp_dir)
  else:
    output_file = re.sub(".in",".out",path)
    if output_file != path:
      os.system("ln %s %s" % (path,tmp_dir))
      os.system("ln %s %s" % (output_file,tmp_dir))
    else:
         if (path.endswith("timeout")):
           os.system("ln %s %s" % (path,tmp_dir))

def make_tmp_dir(repository,tmp_dir):
  os.system("mkdir %s" % tmp_dir)
  for path in repository:
    populate_tmp_dir(path,tmp_dir)

def compile():
  status = os.system("javac -cp . *.java 2> %s/compile.err" % tmp_dir)
  #return status
  return 0

def getTimeOutDir(path, timeoutdef):
  retorno = timeoutdef
  file = path+"/timeout"

  if (os.path.exists(file)):
    infile = open(file,"r")
    line = infile.readline()
    infile.close()
    retorno = int(line)
        
  return retorno
   
def evaluate_file(path, timeout):
  if fnmatch.fnmatch(path,"*.in"):
    sys.stdout.write("    Archivo " + path + " ... ")
    sys.stdout.flush()
    try:
      output = re.sub(".in",".out",path)
      files = { 'in' : path, 'out' : output+"_tmp" }

      cmd="(ulimit -c 0; ulimit -t "+ str(timeout)+"; java -cp . Main %(in)s %(out)s 1>&2 2>/dev/null)"

#      cmd="(ulimit -c 0; ulimit -t "+ str(timeout)+"; java -cp . Main %(in)s > %(out)s 2>/dev/null)"

      status = os.system(cmd % files)
      print "[status=%d]" % status
    except RuntimeError:
      print "[excepcion de corrida]"

def evaluate_dir(path, timeout):
  timeoutDir = getTimeOutDir(path, timeout)
  print "\n    Visitando directorio " + path + " con time out " + str(timeoutDir) + "\n"
  sys.stdout.flush()

  ll = sorted(os.listdir(path))

  for fname in ll:
    efname = path + "/" + fname
    evaluate(efname, timeoutDir)

def evaluate(path, timeout):
  if os.path.isdir(path):
    evaluate_dir(path, timeout)
  else:
    evaluate_file(path, timeout)

def compare_file(fname1,fname2):
  if not os.path.exists(fname1):
    print "Error: %s debe existir en repositorio" % fname1
    return (0,0)

  if not os.path.exists(fname2):
    return (0,1)

  status = os.system("diff -b -B -w %s %s > /dev/null" % (fname1,fname2))
  if status == 0:
    return (1,1)
  else:
    print "Error: "+ fname1
    return (0,1)

def stats_file(path):
  if fnmatch.fnmatch(path,"*.in"):
    fname1 = re.sub(".in",".out",path)
    fname2 = re.sub(".in",".out_tmp",path)

    return compare_file(fname1,fname2)
  else:
    return (0,0)

def stats_dir(path):
  num_match = 0.0
  num_files = 0.0
  for fname in os.listdir(path):
    efname = path + "/" + fname
    (m,f) = stats(efname)
    num_match += m
    num_files += f
  return (num_match,num_files)

def stats(path):
  if os.path.isdir(path):
    return stats_dir(path)
  else:
    return stats_file(path)

def hacerClean():
   sys.stdout.write("Eliminando todos los .class y archivos temporales... ")
   sys.stdout.flush()
   clean()
   print "hecho!"

# main
if len(sys.argv) < 2:
  print "Error: debe especificar al menos un camino a repositorios de casos de prueba."
  raise SystemExit

if sys.argv[1] == "-ayuda" or sys.argv[1] == "-help" or sys.argv[1] == "-?":
  usage()
  raise SystemExit

max = 0
timeout=60

if len(sys.argv) == 4:
  timeout = int(sys.argv[1])
  nota = int(sys.argv[2])
  max = 2

if len(sys.argv) == 3:
  nota = int(sys.argv[1])
  max = 1


print "Time out: " + str(timeout)
print "Nota : " + str(nota)

# verify repository
repository = sys.argv[max+1:]

sys.stdout.write("Verificando los repositorios:")
for path in copy.copy(repository):
  if os.path.exists(path):
    sys.stdout.write(" ")
    sys.stdout.write(path)
    sys.stdout.write(" [ok]")
  else:
    sys.stdout.write(" ")
    sys.stdout.write(path)
    sys.stdout.write(" [err]")
    repository.remove(path)
sys.stdout.write("\n")

# perform clean
hacerClean()

# make tmp subdir
sys.stdout.write("Creando directorio temporal de resultados... ")
sys.stdout.flush()
make_tmp_dir(repository,tmp_dir)
print "hecho!"

# compile
sys.stdout.write("Compilando... ")
sys.stdout.flush()
compile_status = compile()
print "hecho!"

if compile_status != 0:
  print "Error durante la compilacion. Ver %s/compile.err" % tmp_dir
  raise SystemExit

# evaluate
print "Evaluando los repositorios:"
for path in repository:
  fname = tmp_dir + "/" + os.path.basename(path)
  evaluate(fname, timeout)

# compute stats
sys.stdout.write("Computando estadisticas... ")
sys.stdout.flush()
num_match = 0.0
num_files = 0.0
for path in repository:
  fname = tmp_dir + "/" + os.path.basename(path)
  (m,f) = stats(fname)
  num_match += m
  num_files += f

# perform clean
os.system("rm -fr *.class *~ ")
print "hecho"

if compile_status != 0:
  print "Error durante la compilacion. Ver %s/compile.err" % tmp_dir
  raise SystemExit

if num_files > 0:
  porce=100.0*num_match/num_files
  notaF=nota*porce/100.0

  print "resultado=(%f,%f), %%=%f Nota=%f" % (num_match, num_files,
                                              porce, notaF)
else:
  print "[error]"


