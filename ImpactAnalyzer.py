import requests

def fetch_github_code_file(code_file_url):
    try:
        response = requests.get(code_file_url)
        
        if response.status_code == 200:
            return response.text
        
        else:
            print(f"Failed to fetch code from {code_file_url}. Status code: {response.status_code}")
            return None
        
    except Exception as e:
        print(f"Error fetching code: {e}")
        return None


def check_api_endpoints(code, api_endpoints, file_url):
    for endpoint in api_endpoints:
        if endpoint in code:
            print(f"API endpoint '{endpoint}' found in file {file_url}")


if __name__ == "__main__":
    github_code_file_urls = [
        'https://github.com/yyusufcoskun/Stajcell-Paycell-API-Project/blob/main/src/main/java/com/StajcellProject/Stajcell/API/Project/service/TaskServiceImpl.java',
        'https://github.com/yyusufcoskun/quota-tracker/blob/main/quotascrape.ipynb',
        'https://github.com/yyusufcoskun/Stajcell-Paycell-API-Project/blob/main/src/main/java/com/StajcellProject/Stajcell/API/Project/service/TaskService.java',
        'https://github.com/yyusufcoskun/Stajcell-Paycell-API-Project/blob/main/src/main/java/com/StajcellProject/Stajcell/API/Project/controller/TaskController.java'      
        ]
    
    api_endpoints = [
        '/api/tasks',
        '/api/clear',
    ]

    for file_url in github_code_file_urls:
    # Fetch code from GitHub code file URL
        code = fetch_github_code_file(file_url)

        if code is not None:
            # Call the function to check for API endpoints, passing the file URL
            check_api_endpoints(code, api_endpoints, file_url)

