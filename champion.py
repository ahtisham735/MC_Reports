import requests #importing request module to use request library
numofcasesfound=dict() #to store number of found cases against each collection

def champion_clothing(filename):
	data=requests.get(' https://jsonkeeper.com/b/ZVOV')
	data=data.json()	
	try:
		with open(filename) as file: #'opening file using 'with' so that it will be closed automatically
			cases=int(file.readline())
			for _ in range(cases):
				line=file.readline()
				line=line.split(',')
				numofcasesfound[line[0]]=0
				for item in data:
					if item['collection']==line[0]:
						if percentage(item)>= int(line[1]):
								numofcasesfound[line[0]]+=1
			
								
	except FileNotFoundError:
		print("No such file exist")
	except Exception as e:
		print(e.__class__)


def percentage(item):	#utility function to find percentage
	percentage=int(item['previous_price'])-int(item['price'])
	percentage=(percentage*100)/int(item['previous_price'])
	return percentage
def print_case(foundcases): #utility function to print cases
	for case in foundcases:
		print(foundcases[case])

champion_clothing('input.txt')
print_case(numofcasesfound)	