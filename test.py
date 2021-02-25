import subprocess
import sys

from modulefinder import ModuleFinder

def install(package):
	subprocess.check_call([sys.executable, "-m", "pip", "install", package])

def checkPackage(package):
	finder = ModuleFinder()
	finder.run_script("fetcher.py")

	print("Loaded Modules: ")
	for name, mod, in finder.modules.items():
		print("{}: ".format(name))
		print(','.join(finder(list(mod.globalnames.keys()) [:3])))


if __name__ == "__main__":
	print("Testing finding and installing necessary packages")
	
